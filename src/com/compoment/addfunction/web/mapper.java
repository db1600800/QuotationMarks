package com.compoment.addfunction.web;

import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;

public class mapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void mapperXml(List<TableBean> tables) {

		String show = "";
		String condition = "";
		String relate = "";
		
		boolean haveRelate=false;

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					show += " " + column.belongWhichTable.tableEnName + "." + column.columnEnName + ",";

				} else if ("right".equals(column.rightClickSelected)) {
					condition += " " + column.belongWhichTable.tableEnName + "." + column.columnEnName + "= ,and";
				} else {

				}

				if (column.relateColumnBeans != null && column.relateColumnBeans.size() > 0) {
					haveRelate=true;
					relate += column.belongWhichTable.tableEnName;
					// 关联的
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

						if (column.relateColumnBeans.size() == 1) {// 两表

							relate += " inner join " + relateColumn.belongWhichTable.tableEnName + " on "
									+ column.belongWhichTable.tableEnName + "." + column.columnEnName + "="
									+ relateColumn.belongWhichTable.tableEnName + "." + relateColumn.columnEnName;

						} else if (column.relateColumnBeans.size() > 1) {// 三表或以上
							relate += " inner join " + relateColumn.belongWhichTable.tableEnName + " on "
									+ column.belongWhichTable.tableEnName + "." + column.columnEnName + "="
									+ relateColumn.belongWhichTable.tableEnName + "." + relateColumn.columnEnName;

						}

					}
				}
			}
		}

		
		
		
		
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {
		
		
	if(!haveRelate)
		{//单个表
			relate = column.belongWhichTable.tableEnName;
		}
		
		}
	}
		
		
		
		
		
		
		
		String sql="";
		
		if("".equals(show) && "".equals(condition))
		{
			 sql = "select * from " + relate ;
		}
		
		else if("".equals(show) && !"".equals(condition))
		{
			 sql = "select * from " + relate + " where "
						+ condition.substring(0, condition.lastIndexOf(",and"));
		}else if(!"".equals(show) && "".equals(condition))
		{
			 sql = "select " + show.substring(0, show.lastIndexOf(",")) + " from " + relate ;
		}
		else
		{
			 sql = "select " + show.substring(0, show.lastIndexOf(",")) + " from " + relate + " where "
						+ condition.substring(0, condition.lastIndexOf(",and"));
		}
		

		System.out.println("查询:" + sql);
		
		
		String m="";
		
		m+="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
		m+="<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n";
		m+="<mapper namespace=\"com.cpz.dao.impl.ErrorInfoMapper\">\n";
		
		
		m+="	<resultMap id=\"BaseResultMap\" type=\"ErrorInfo\">\n";
		m+="		<id column=\"id\" property=\"id\" jdbcType=\"INTEGER\" />\n";
		m+="		<result column=\"errorCode\" property=\"errorCode\" jdbcType=\"VARCHAR\" />\n";
		m+="		<result column=\"errorType\" property=\"errorType\" jdbcType=\"VARCHAR\" />\n";
		m+="		<result column=\"errorMsg\" property=\"errorMsg\" jdbcType=\"VARCHAR\" />\n";
		m+="	</resultMap>\n";
		m+="	<sql id=\"Base_Column_List\">\n";
		m+="		id, errorCode, errorType, errorMsg\n";
		m+="	</sql>\n";
		m+="	<select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\"\n";
		m+="		parameterType=\"java.lang.Integer\">\n";
		m+="		select\n";
		m+="		<include refid=\"Base_Column_List\" />\n";
		m+="		from t_gsp_error_info\n";
		m+="		where id = #{id,jdbcType=INTEGER}\n";
		m+="	</select>\n";
		m+="	<select id=\"selectByCodeOrType\" resultMap=\"BaseResultMap\">\n";
		m+="		select\n";
		m+="		<include refid=\"Base_Column_List\" />\n";
		m+="		from t_gsp_error_info\n";
		m+="		\n";
		m+="		where 1=1\n";
		m+="		<if test=\"errorCode != null and errorCode != ''\">\n";
		m+="			and errorCode = #{errorCode}\n";
		m+="		</if>\n";
		m+="		<if test=\"errorType != null and errorType != ''\">\n";
		m+="			and errorType = #{errorType}\n";
		m+="		</if>\n";
		m+="	</select>\n";
		m+="	<delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang.Integer\">\n";
		m+="		delete from t_gsp_error_info\n";
		m+="		where id = #{id,jdbcType=INTEGER}\n";
		m+="	</delete>\n";
		m+="	<insert id=\"insert\" parameterType=\"ErrorInfo\">\n";
		m+="		insert into t_gsp_error_info (id, errorCode, errorType,\n";
		m+="		errorMsg)\n";
		m+="		values (#{id,jdbcType=INTEGER}, #{errorCode,jdbcType=VARCHAR},\n";
		m+="		#{errorType,jdbcType=VARCHAR},\n";
		m+="		#{errorMsg,jdbcType=VARCHAR})\n";
		m+="	</insert>\n";
		m+="	<insert id=\"insertSelective\" parameterType=\"ErrorInfo\">\n";
		m+="		insert into t_gsp_error_info\n";
		m+="		<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n";
		m+="			<if test=\"id != null\">\n";
		m+="				id,\n";
		m+="			</if>\n";
		m+="			<if test=\"errorCode != null\">\n";
		m+="				errorCode,\n";
		m+="			</if>\n";
		m+="			<if test=\"errorType != null\">\n";
		m+="				errorType,\n";
		m+="			</if>\n";
		m+="			<if test=\"errorMsg != null\">\n";
		m+="				errorMsg,\n";
		m+="			</if>\n";
		m+="		</trim>\n";
		m+="		<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n";
		m+="			<if test=\"id != null\">\n";
		m+="				#{id,jdbcType=INTEGER},\n";
		m+="			</if>\n";
		m+="			<if test=\"errorCode != null\">\n";
		m+="				#{errorCode,jdbcType=VARCHAR},\n";
		m+="			</if>\n";
		m+="			<if test=\"errorType != null\">\n";
		m+="				#{errorType,jdbcType=VARCHAR},\n";
		m+="			</if>\n";
		m+="			<if test=\"errorMsg != null\">\n";
		m+="				#{errorMsg,jdbcType=VARCHAR},\n";
		m+="			</if>\n";
		m+="		</trim>\n";
		m+="	</insert>\n";
		m+="	<update id=\"updateByPrimaryKeySelective\" parameterType=\"ErrorInfo\">\n";
		m+="		<!-- WARNING - @mbggenerated This element is automatically generated by \n";
		m+="			MyBatis Generator, do not modify. This element was generated on Mon May 09 \n";
		m+="			14:23:05 CST 2016. -->\n";
		m+="		update t_gsp_error_info\n";
		m+="		<set>\n";
		m+="			<if test=\"errorCode != null\">\n";
		m+="				errorCode = #{errorCode,jdbcType=VARCHAR},\n";
		m+="			</if>\n";
		m+="			<if test=\"errorType != null\">\n";
		m+="				errorType = #{errorType,jdbcType=VARCHAR},\n";
		m+="			</if>\n";
		m+="			<if test=\"errorMsg != null\">\n";
		m+="				errorMsg = #{errorMsg,jdbcType=VARCHAR},\n";
		m+="			</if>\n";
		m+="		</set>\n";
		m+="		where id = #{id,jdbcType=INTEGER}\n";
		m+="	</update>\n";
		m+="	<update id=\"updateByPrimaryKey\" parameterType=\"ErrorInfo\">\n";
		m+="		<!-- WARNING - @mbggenerated This element is automatically generated by \n";
		m+="			MyBatis Generator, do not modify. This element was generated on Mon May 09 \n";
		m+="			14:23:05 CST 2016. -->\n";
		m+="		update t_gsp_error_info\n";
		m+="		set errorCode = #{errorCode,jdbcType=VARCHAR},\n";
		m+="		errorType = #{errorType,jdbcType=VARCHAR},\n";
		m+="		errorMsg = #{errorMsg,jdbcType=VARCHAR}\n";
		m+="		where id = #{id,jdbcType=INTEGER}\n";
		m+="	</update>\n";
		m+="</mapper>\n";

	}
	
	
	public void entity()
	{
		String m="";
		m+="public class ErrorInfo {\n";
		m+="	private Integer id;\n";

		m+="	private String errorCode;\n";

		m+="	private String errorType;\n";

		m+="	private String errorMsg;\n";

		m+="	public Integer getId() {\n";
		m+="		return id;\n";
		m+="	}\n";

		m+="	public void setId(Integer id) {\n";
		m+="		this.id = id;\n";
		m+="	}\n";

		m+="	public String getErrorCode() {\n";
		m+="		return errorCode;\n";
		m+="	}\n";

		m+="	public void setErrorCode(String errorCode) {\n";
		m+="		this.errorCode = errorCode;\n";
		m+="	}\n";

		m+="	public String getErrorType() {\n";
		m+="		return errorType;\n";
		m+="	}\n";

		m+="	public void setErrorType(String errorType) {\n";
		m+="		this.errorType = errorType;\n";
		m+="	}\n";

		m+="	public String getErrorMsg() {\n";
		m+="		return errorMsg;\n";
		m+="	}\n";

		m+="	public void setErrorMsg(String errorMsg) {\n";
		m+="		this.errorMsg = errorMsg;\n";
		m+="	}\n";
		m+="}\n";

	}
	
	
	public void serviceInterface()
	{
		String m="";
		m+="public interface ErrorService {\n";

		m+="	/**\n";
		m+="	 * 根据错误码，错误类型 获取错误信息\n";
		m+="	 * @param string\n";
		m+="	 * @param string2\n";
		m+="	 * @return\n";
		m+="	 */\n";
		m+="	ErrorInfo getErrorInfo(String errorCode, String errorType) throws CommonException;\n";

		m+="}\n";

	}
	
	
	public void service()
	{
		String m="";
		m+="import java.util.HashMap;\n";
		m+="import java.util.Map;\n";

		m+="import javax.annotation.Resource;\n";

		m+="import org.slf4j.Logger;\n";
		m+="import org.slf4j.LoggerFactory;\n";
		m+="import org.springframework.stereotype.Service;\n";

		m+="import com.framework.dao.BaseDao;\n";
		m+="import com.framework.dao.common.DaoTools;\n";
		m+="import com.framework.exception.CommonException;\n";
		m+="import com.cpz.pojo.ErrorInfo;\n";
		m+="import com.cpz.service.ErrorService;\n";

		m+="/** \n";
		m+="* @author tang E-mail: killerover84@163.com\n";
		m+="* @version 2016年5月9日 下午1:49:20 \n";
		m+="* 类说明 \n";
		m+="*/\n";
		m+="@Service(\"errorService\")\n";
		m+="public class ErrorServiceImpl implements ErrorService {\n";
		m+="	private final static Logger logger = LoggerFactory.getLogger(ErrorServiceImpl.class);\n";
		m+="	\n";
		m+="	@Resource\n";
		m+="	private BaseDao baseDao;\n";
		m+="	\n";
		m+="	@Override\n";
		m+="	public ErrorInfo getErrorInfo(String errorCode, String errorType) throws CommonException {\n";
		m+="		// TODO Auto-generated method stub\n";
		m+="		Map<String,Object> m = new HashMap<>();\n";
		m+="		m.put(\"errorCode\", errorCode);\n";
		m+="		m.put(\"errorType\", errorType);\n";
		m+="		\n";
		m+="		Object obj = baseDao.selectOne(DaoTools.getMapperNamespace(ErrorInfo.class, \"selectByCodeOrType\"), m);\n";
		m+="		return obj == null ? null : (ErrorInfo)obj;\n";
		m+="	}\n";

		m+="}\n";

	}
	
	
	public void controller()
	{
		String m="";
		m+="import java.util.Locale;\n";
		m+="import java.util.Map;\n";
		m+="import java.util.Set;\n";

		m+="import javax.validation.ConstraintViolation;\n";
		m+="import javax.validation.Validator;\n";

		m+="import org.springframework.context.annotation.Scope;\n";
		m+="import org.springframework.context.i18n.LocaleContextHolder;\n";
		m+="import org.springframework.stereotype.Controller;\n";
		m+="import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;\n";
		m+="import org.springframework.web.bind.annotation.RequestBody;\n";
		m+="import org.springframework.web.bind.annotation.RequestMapping;\n";
		m+="import org.springframework.web.bind.annotation.RequestParam;\n";
		m+="import org.springframework.web.bind.annotation.ResponseBody;\n";
		m+="import org.springframework.web.servlet.i18n.SessionLocaleResolver;\n";
		m+="import org.springframework.web.servlet.support.RequestContext;\n";

		m+="import com.framework.controller.BaseController;\n";
		m+="import com.framework.utils.SpringBeanManger;\n";
		m+="import com.cpz.utils.CommonUtil;\n";
		m+="import com.cpz.utils.Constant;\n";

		m+="/**\n";
		m+=" * @author tang E-mail: killerover84@163.com\n";
		m+=" * @version 2016年5月16日 上午9:05:38 类说明\n";
		m+=" */\n";
		m+="@Controller\n";
		m+="@Scope(\"prototype\")\n";
		m+="@RequestMapping(\"/front/i18n\")\n";
		m+="public class I18n extends BaseController {\n";

		m+="	// 国际化\n";
		m+="	@RequestMapping(\"/inter.do\")\n";
		m+="	public @ResponseBody Map<String, Object> inter(@RequestParam Map reqMap) {\n";

		m+="		if (null == reqMap || reqMap.isEmpty())\n";
		m+="			return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

		m+="		String langType = reqMap.get(\"langType\") == null ? null : reqMap.get(\"langType\").toString();\n";
		m+="		if (null == langType || langType.isEmpty())\n";
		m+="			langType = \"zh\";\n";

		m+="		if (langType.equals(\"zh\")) {\n";
		m+="			Locale locale = new Locale(\"zh\", \"CN\");\n";
		m+="			getRequest().getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);\n";
		m+="		} else if (langType.equals(\"en\")) {\n";
		m+="			Locale locale = new Locale(\"en\", \"US\");\n";
		m+="			getRequest().getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);\n";
		m+="		} else\n";
		m+="			getRequest().getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,\n";
		m+="					LocaleContextHolder.getLocale());\n";

		m+="		// 从后台代码获取国际化信息\n";
		m+="		RequestContext requestContext = new RequestContext(getRequest());\n";
		m+="		System.out.println(requestContext.getMessage(\"xxx\"));\n";
		m+="		System.out.println(SpringBeanManger.getTextValue(\"xxx\"));\n";

		m+="		return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);\n";
		m+="	}\n";
		m+="}\n";

	}

}
