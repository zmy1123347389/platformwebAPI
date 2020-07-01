package com.behere.portal.util;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
/**
 * mybatis查询封装的分页信息实体对象
 * 
 * @author NanBo
 * @version V1.0.0-RELEASE 日期：2016-4-20
 * @since 1.0.0-RELEASE
 */
public class PageQuery extends RowBounds implements java.io.Serializable {

	private static final long serialVersionUID = -8000900575354501298L;

	// 当前页
	private int curPage;

	// 起始数据条数
	private int start;

	// 每页多少条
	private int length = 10;

	// 表格所有列
	private String sColumns;

	// 待排序的列索引
	private String columnIndex;

	// 排序规则
	private String sortTypes;

	// datatables参数
	private String draw;

	// 是否需要计算总数,默认1为不计算
	private String isCount = "0";

	// 是否需要计算总计,默认1为不计算
	private String isTotal = "1";

	// 查询排序列集合
	private List<SortRule> sortRuleList;

	// 无参构造器
	public PageQuery() {
		super();
	}

	/**
	 * PageQuery有参构造器。
	 * 
	 * @param curPage
	 *            当前页。
	 * @param pageSize
	 *            每页显示多少条。
	 */
	public PageQuery(int curPage, int length) {
		this.curPage = curPage;
		this.length = length;
		this.start = (curPage - 1) * length;//
		this.sortRuleList = new ArrayList<SortRule>();
	}

	/**
	 * PageQuery有参构造器。
	 * 
	 * @param curPage
	 *            当前页。
	 * @param pageSize
	 *            每页显示多少条。
	 * @param sortColumn
	 *            排序对象。
	 */
	public PageQuery(int curPage, int length, SortRule sortColumn) {
		this.curPage = curPage;
		this.length = length;
		this.start = (curPage - 1) * length;//
		this.sortRuleList = new ArrayList<SortRule>();
		if (sortColumn != null) {
			this.sortRuleList.add(sortColumn);
		}
	}

	/**
	 * PageQuery有参构造器。
	 * 
	 * @param curPage
	 *            当前页。
	 * @param pageSize
	 *            每页显示多少条。
	 * @param sortColumnList
	 *            排序对象集合。
	 */
	public PageQuery(int curPage, int length,
			List<SortRule> sortColumnList) {
		this.curPage = curPage;
		this.length = length;
		this.start = (curPage - 1) * length;//
		this.sortRuleList = sortColumnList;
	}

	/**
	 * PageQuery有参构造器。
	 * 
	 * @param curPage
	 *            当前页。
	 * @param pageSize
	 *            每页显示多少条。
	 * @param sortRules
	 *            排序字符串。
	 */
	public PageQuery(int curPage, int length, String sortRules) {
		this(curPage, length, SortRule.parseSortColumns(sortRules));
	}

	/**
	 * 每页显示多少条。
	 * 
	 * @return 每页显示多少条。
	 */
	public int getLimit() {
		return length;
	}

	/**
	 * sortRuleList的get方法。
	 * 
	 * @return 排序规则集合。
	 */
	public List<SortRule> getSortRuleList() {
		if (null != columnIndex && !"".equals(columnIndex) && null != sColumns
				&& !"".equals(sColumns) && null != sortTypes
				&& !"".equals(sortTypes)) {
			String[] allColumns = sColumns.split(",");
			String[] allColumnIndex = columnIndex.split(",");
			String[] allSortDir = sortTypes.split(",");
			for (int i = 0; i < allColumnIndex.length; i++) {
				String sortName = allColumns[Integer
						.parseInt(allColumnIndex[i])];
				String sortDir = allSortDir[i];
				SortRule sortRule = new SortRule(sortName, sortDir);
				if (this.sortRuleList == null) {
					this.sortRuleList = new ArrayList<SortRule>();
				}
				if (this.sortRuleList.size() == 0) {
					this.sortRuleList.add(sortRule);
				} else {
					for (int j = 0; j < this.sortRuleList.size(); j++) {
						boolean flag = false;
						for (int k = 0; k < this.sortRuleList.size(); k++) {
							SortRule sort = this.sortRuleList.get(k);
							if (sort.getSortType().equals(
									sortRule.getSortType())
									&& sort.getColumnName().equals(
											sortRule.getColumnName())) {
								flag = true;
							}
						}
						if (!flag) {
							this.sortRuleList.add(sortRule);
						}
					}
				}
			}
		}
		return sortRuleList;
	}

	/**
	 * sortRuleList的set方法。
	 */
	public void setSortRuleList(List<SortRule> sortRuleList) {
		this.sortRuleList = sortRuleList;
	}

	/**
	 * 分页的偏移量。
	 * 
	 * @return 分页偏移多少条。
	 */
	public int getOffset() {
		return start;
	}

	/**
	 * curPage的get方法。
	 * 
	 * @return 当前页。
	 */
	public int getCurPage() {
		curPage = start / length + 1;
		return curPage;
	}

	/**
	 * curPage的set方法。
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	/**
	 * start的get方法。
	 */
	public int getStart() {
		return start;
	}

	/**
	 * start的set方法。
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * length的get方法。
	 */
	public int getLength() {
		return length;
	}

	/**
	 * length的set方法。
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * sColumns的get方法。
	 */
	public String getsColumns() {
		return sColumns;
	}

	/**
	 * sColumns的set方法。
	 */
	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	/**
	 * ColumnIndex的get方法。
	 */
	public String getColumnIndex() {
		return columnIndex;
	}

	/**
	 * ColumnIndex的set方法。
	 */
	public void setColumnIndex(String columnIndex) {
		this.columnIndex = columnIndex;
	}

	/**
	 * SortTypes的get方法。
	 */
	public String getSortTypes() {
		return sortTypes;
	}

	/**
	 * SortTypes的set方法。
	 */
	public void setSortTypes(String sortTypes) {
		this.sortTypes = sortTypes;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	/**
	 * isCount的get方法。
	 */
	public String getIsCount() {
		return isCount;
	}

	/**
	 * isCount的set方法。
	 */
	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}

	/**
	 * isTotal的get方法。
	 */
	public String getIsTotal() {
		return isTotal;
	}

	/**
	 * isTotal的set方法。
	 */
	public void setIsTotal(String isTotal) {
		this.isTotal = isTotal;
	}
}