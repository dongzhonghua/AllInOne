package xyz.dsvshx.blog.entity;

import java.util.ArrayList;
import java.util.List;

public class ArticleLikesRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleLikesRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andArticleidIsNull() {
            addCriterion("articleId is null");
            return (Criteria) this;
        }

        public Criteria andArticleidIsNotNull() {
            addCriterion("articleId is not null");
            return (Criteria) this;
        }

        public Criteria andArticleidEqualTo(Long value) {
            addCriterion("articleId =", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidNotEqualTo(Long value) {
            addCriterion("articleId <>", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidGreaterThan(Long value) {
            addCriterion("articleId >", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidGreaterThanOrEqualTo(Long value) {
            addCriterion("articleId >=", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidLessThan(Long value) {
            addCriterion("articleId <", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidLessThanOrEqualTo(Long value) {
            addCriterion("articleId <=", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidIn(List<Long> values) {
            addCriterion("articleId in", values, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidNotIn(List<Long> values) {
            addCriterion("articleId not in", values, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidBetween(Long value1, Long value2) {
            addCriterion("articleId between", value1, value2, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidNotBetween(Long value1, Long value2) {
            addCriterion("articleId not between", value1, value2, "articleid");
            return (Criteria) this;
        }

        public Criteria andLikeridIsNull() {
            addCriterion("likerId is null");
            return (Criteria) this;
        }

        public Criteria andLikeridIsNotNull() {
            addCriterion("likerId is not null");
            return (Criteria) this;
        }

        public Criteria andLikeridEqualTo(Integer value) {
            addCriterion("likerId =", value, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridNotEqualTo(Integer value) {
            addCriterion("likerId <>", value, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridGreaterThan(Integer value) {
            addCriterion("likerId >", value, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridGreaterThanOrEqualTo(Integer value) {
            addCriterion("likerId >=", value, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridLessThan(Integer value) {
            addCriterion("likerId <", value, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridLessThanOrEqualTo(Integer value) {
            addCriterion("likerId <=", value, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridIn(List<Integer> values) {
            addCriterion("likerId in", values, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridNotIn(List<Integer> values) {
            addCriterion("likerId not in", values, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridBetween(Integer value1, Integer value2) {
            addCriterion("likerId between", value1, value2, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikeridNotBetween(Integer value1, Integer value2) {
            addCriterion("likerId not between", value1, value2, "likerid");
            return (Criteria) this;
        }

        public Criteria andLikedateIsNull() {
            addCriterion("likeDate is null");
            return (Criteria) this;
        }

        public Criteria andLikedateIsNotNull() {
            addCriterion("likeDate is not null");
            return (Criteria) this;
        }

        public Criteria andLikedateEqualTo(String value) {
            addCriterion("likeDate =", value, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateNotEqualTo(String value) {
            addCriterion("likeDate <>", value, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateGreaterThan(String value) {
            addCriterion("likeDate >", value, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateGreaterThanOrEqualTo(String value) {
            addCriterion("likeDate >=", value, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateLessThan(String value) {
            addCriterion("likeDate <", value, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateLessThanOrEqualTo(String value) {
            addCriterion("likeDate <=", value, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateLike(String value) {
            addCriterion("likeDate like", value, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateNotLike(String value) {
            addCriterion("likeDate not like", value, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateIn(List<String> values) {
            addCriterion("likeDate in", values, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateNotIn(List<String> values) {
            addCriterion("likeDate not in", values, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateBetween(String value1, String value2) {
            addCriterion("likeDate between", value1, value2, "likedate");
            return (Criteria) this;
        }

        public Criteria andLikedateNotBetween(String value1, String value2) {
            addCriterion("likeDate not between", value1, value2, "likedate");
            return (Criteria) this;
        }

        public Criteria andIsreadIsNull() {
            addCriterion("isRead is null");
            return (Criteria) this;
        }

        public Criteria andIsreadIsNotNull() {
            addCriterion("isRead is not null");
            return (Criteria) this;
        }

        public Criteria andIsreadEqualTo(Boolean value) {
            addCriterion("isRead =", value, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadNotEqualTo(Boolean value) {
            addCriterion("isRead <>", value, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadGreaterThan(Boolean value) {
            addCriterion("isRead >", value, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isRead >=", value, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadLessThan(Boolean value) {
            addCriterion("isRead <", value, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadLessThanOrEqualTo(Boolean value) {
            addCriterion("isRead <=", value, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadIn(List<Boolean> values) {
            addCriterion("isRead in", values, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadNotIn(List<Boolean> values) {
            addCriterion("isRead not in", values, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadBetween(Boolean value1, Boolean value2) {
            addCriterion("isRead between", value1, value2, "isread");
            return (Criteria) this;
        }

        public Criteria andIsreadNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isRead not between", value1, value2, "isread");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}