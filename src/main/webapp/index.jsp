<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="com.xinnuo.se.search.SearchResultBean" %>
<%@ page import="java.util.List" %>
<%
    String searchWord = (String) request.getAttribute("w");
    if (searchWord == null) {
        searchWord = "";
    }
%>
<HTML>
<HEAD>
    <TITLE>Lucene搜索引擎</TITLE>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
</HEAD>
<BODY>
<CENTER>
    <h1>
        Lucene 搜索引擎
    </h1>
    <div class="search">
        <FORM action="s">
            <TABLE>
                <TBODY>
                <TR>
                    <TD colspan="3">
                        <INPUT name="w" type="text" size="40" value="<%=searchWord%>">
                        <INPUT type="submit" value="搜索">
                    </TD>
                </TR>
                </TBODY>
            </TABLE>
        </FORM>
    </div>
</CENTER>
<TABLE class="result" id="result">
    <TBODY>
    <%
        List searchResult = (List) request.getAttribute("searchResult");
        int resultCount = 0;
        if (null != searchResult) {
            resultCount = searchResult.size();
        }
        for (int i = 0; i < resultCount; i++) {
            SearchResultBean resultBean = (SearchResultBean) searchResult.get(i);
            String snap = resultBean.getHtmlSnap();
            String title = resultBean.getHtmlTitle();
            String path = resultBean.getHtmlPath();
            title = title.replace(searchWord, "<font color='red'>" + searchWord + "</font>");
            snap = snap.replace(searchWord, "<font color='red'>" + searchWord + "</font>");
    %>
    <TR>
        <TD class="title">
            <h3>
                <A href="<%=path%>" target="_blank"><%=title%>
                </A>
            </h3>
        </TD>
    </TR>
    <TR>
        <TD class="title">
            <%=snap%>
            &nbsp;&nbsp;-快照
        </TD>
    </TR>
    <tr>
        <td>
            <hr/>
        </td>
    </tr>
    <%}%>
    </TBODY>
</TABLE>
</BODY>
</HTML>
