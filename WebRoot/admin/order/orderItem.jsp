<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table width="100%" border="1" cellSpacing="1" cellPadding="0"
	align="center" bgColor="#f5fafe">
	<tr>
		<td>商品图片</td>
		<td>商品名称</td>
		<td>购买数量</td>
		<td>金额总计</td>
	</tr>
	<s:iterator var="orderItem" value="list">
		<tr>
			<td><img width="40" height="45"
				src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"></td>
			<td><s:property value="#orderItem.product.pname" /></td>
			<td><s:property value="#orderItem.count" /></td>
			<td><s:property value="#orderItem.subtotal" /></td>
		</tr>
	</s:iterator>
</table>