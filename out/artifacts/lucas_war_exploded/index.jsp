<%--
  Created by IntelliJ IDEA.
  User: ken
  Date: 15/7/30
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <p>HTML 5学习总结</p>
  <ul>
    <li>让HMLT5文档更结构化的一系列标签 header,footer,article, section, aside等</li>
    <li>让所有text,lable可以被用户修改. contenteditable, document.designMode</li>
    <li>同一个Form表单可以使用不同的提交method, 不同的action</li>
    <li>Lable和input组成一个整体,并且可以通过lable的control属性来获取input对象. </li>
    <li>Placeholder属性可以在input text里面显示提示信息</li>
    <li>list属性可以让input text来实现可编辑的下拉框的功能</li>
    <li>autocomplete属性可以让input text可以根据用户输入从一个list里面选取一个值</li>
    <li>pattern让input text支持正则表达式检查</li>
    <li>image提交按钮</li>
    <li>input有很多增强的功能并且能自动支持类型验证, type= url, email, number, date, time, datetime-local,week,month,range,color,output</li>
    <li>details支持展开和关闭</li>
    <li>figure适合整合图片,音频,视频等内容</li>
  </ul>

  <p>parameter=<%=request.getParameter("user") %></p>

  <p> Examples: <br/>
    User form and servlet: <a href="form.html"> click me</a>

  </p>
  </body>
</html>
