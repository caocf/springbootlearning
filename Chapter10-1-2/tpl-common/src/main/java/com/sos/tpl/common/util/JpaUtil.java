package com.sos.tpl.common.util;

import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ELEMENT_COLLECTION;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.MANY_TO_MANY;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.MANY_TO_ONE;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ONE_TO_MANY;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ONE_TO_ONE;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.util.Assert;

public class JpaUtil {
	public static Map<PersistentAttributeType, Class<? extends Annotation>> ASSOCIATION_TYPES;
	static {
		Map<PersistentAttributeType, Class<? extends Annotation>> persistentAttributeTypes = new HashMap<PersistentAttributeType, Class<? extends Annotation>>();
		persistentAttributeTypes.put(ONE_TO_ONE, OneToOne.class);
		persistentAttributeTypes.put(ONE_TO_MANY, null);
		persistentAttributeTypes.put(MANY_TO_ONE, ManyToOne.class);
		persistentAttributeTypes.put(MANY_TO_MANY, null);
		persistentAttributeTypes.put(ELEMENT_COLLECTION, null);

		ASSOCIATION_TYPES = Collections
				.unmodifiableMap(persistentAttributeTypes);
	}

	public static Join<?, ?> getOrCreateJoin(From<?, ?> from, String attribute,
			JoinType joinType) {

		for (Join<?, ?> join : from.getJoins()) {

			boolean sameName = join.getAttribute().getName().equals(attribute);

			if (sameName && join.getJoinType().equals(joinType)) {
				return join;
			}
		}
		return from.join(attribute, joinType);
	}
	public static Expression<Object> toExpressionRecursively(Path<Object> path,
			String property,Class<?> domainClass) {
		PropertyPath propertypath = PropertyPath.from(property,domainClass);
		return toExpressionRecursively(path,propertypath);
	}
	public static Expression<Object> toExpressionRecursively(Path<Object> path,
			PropertyPath property) {
		Path<Object> result = path.get(property.getSegment());
		return property.hasNext() ? toExpressionRecursively(result,
				property.next()) : result;
	}
	public static <T> Expression<T> toExpressionRecursively(From<?, ?> from,
			String property, JoinType joinType) {
		PropertyPath propertypath = PropertyPath.from(property, from.getJavaType());
		return	toExpressionRecursively(from,propertypath,joinType);
	}
	@SuppressWarnings("unchecked")
	public static <T> Expression<T> toExpressionRecursively(From<?, ?> from,
			PropertyPath property, JoinType joinType) {

		Bindable<?> propertyPathModel = null;
		Bindable<?> model = from.getModel();
		String segment = property.getSegment();

		if (model instanceof ManagedType) {

			/*
			 * Required to keep support for EclipseLink 2.4.x. TODO: Remove once
			 * we drop that (probably Dijkstra M1) See:
			 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=413892
			 */
			propertyPathModel = (Bindable<?>) ((ManagedType<?>) model)
					.getAttribute(segment);
		} else {
			propertyPathModel = from.get(segment).getModel();
		}

		if (requiresJoin(propertyPathModel, model instanceof PluralAttribute)) {
			Join<?, ?> join = getOrCreateJoin(from, segment, joinType);
			return (Expression<T>) (property.hasNext() ? toExpressionRecursively(
					join, property.next(), joinType) : join);
		} else {
			Path<Object> path = from.get(segment);
			return (Expression<T>) (property.hasNext() ? toExpressionRecursively(
					path, property.next()) : path);
		}
	}

	public static boolean requiresJoin(Bindable<?> propertyPathModel,
			boolean forPluralAttribute) {

		if (propertyPathModel == null && forPluralAttribute) {
			return true;
		}

		if (!(propertyPathModel instanceof Attribute)) {
			return false;
		}

		Attribute<?, ?> attribute = (Attribute<?, ?>) propertyPathModel;

		if (!ASSOCIATION_TYPES.containsKey(attribute
				.getPersistentAttributeType())) {
			return false;
		}

		Class<? extends Annotation> associationAnnotation = ASSOCIATION_TYPES
				.get(attribute.getPersistentAttributeType());

		if (associationAnnotation == null) {
			return true;
		}

		Member member = attribute.getJavaMember();

		if (!(member instanceof AnnotatedElement)) {
			return true;
		}

		Annotation annotation = AnnotationUtils.getAnnotation(
				(AnnotatedElement) member, associationAnnotation);
		return annotation == null ? true : (Boolean) AnnotationUtils.getValue(
				annotation, "optional");
	}
	public static List<javax.persistence.criteria.Order> toOrders(Sort sort, Root<?> root, CriteriaBuilder cb,JoinType joinType) {

		List<javax.persistence.criteria.Order> orders = new ArrayList<javax.persistence.criteria.Order>();

		if (sort == null) {
			return orders;
		}

		Assert.notNull(root);
		Assert.notNull(cb);

		for (org.springframework.data.domain.Sort.Order order : sort) {
			orders.add(toJpaOrder(order, root, cb,joinType));
		}

		return orders;
	}
	@SuppressWarnings("unchecked")
	public static javax.persistence.criteria.Order toJpaOrder(Order order, Root<?> root, CriteriaBuilder cb,JoinType joinType) {
		Expression<?> expression = toExpressionRecursively(root, order.getProperty(),joinType);
		if (order.isIgnoreCase() && String.class.equals(expression.getJavaType())) {
			Expression<String> lower = cb.lower((Expression<String>) expression);
			return order.isAscending() ? cb.asc(lower) : cb.desc(lower);
		} else {
			return order.isAscending() ? cb.asc(expression) : cb.desc(expression);
		}
	}
}
