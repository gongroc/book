<!DOCTYPE html>
<html>
<body>
获取内容: ${message}
<br>
调用方法：${time?datetime}
<br>
字符串：${"输出字符串"}
<br>
计算：${1 + 2}
<br>
判断：<#if 1 == 2 > 等于 <#else> 不等于 </#if>
<br>
遍历：<#list [1,2,3,4] as item> ${item} <#sep>,  </#list>
</body>
</html>
