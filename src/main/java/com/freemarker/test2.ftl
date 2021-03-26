---------- 取整
<#assign x = 5>
${(x/2)?int}
${1.1?int}
${1.999?int}
${-1.9999?int}
${-1.1?int}


---------- 字符串连接
${3+"5"}
${3+5}
---------- 测试金额
<#setting number_format = "currency" />
<#assign price = 42 />
${price}
${price?string}
${price?string.number}
${price?string.currency}
${price?string.percent}

------------测试日期
<#assign lastUpdated = "2009-01-07 15:05"?datetime("yyyy-MM-dd HH:mm") />
${lastUpdated?string("yyyy-MM-dd HH:mm:ss zzzz")}
${lastUpdated?string("EEE,MMM d,yy")}
${lastUpdated?string("EEEE,MMMM dd,yyyy,hh:mm:ss a '('zzz')'")}
${lastUpdated?string.short}
${lastUpdated?string.long}
${lastUpdated?string.full}

-----------测试布尔值：
<#assign foo=true />
${foo?string("是foo","非foo")}


<#assign x = 2.582 />
<#assign y =4 />
#{x;M2}
#{y;M2}
#{x;m1}
#{y;m1}
#{x;m1M2}
#{y;m1M2}

-----------日期格式化
<#assign test1 = "2009-01-22"?date("yyyy-MM-dd") />
<#assign test2 ="16:34:43"?time("HH:mm:ss") />
<#assign test2 = "2009-01-22 17:23:45"?datetime("yyyy-MM-dd HH:mm:ss") />
${test1?string.full}


------------list集合
<#list ["星期一","星期二","星期三","星期四","星期五"] as x>
    ${x};
</#list>

<#list ["Joe", "Fred"] + ["Julia", "Kate"] as user>
    ${user}
</#list>

---截取字符串 list连接
<#list ["Joe", "Fred"] + ["Julia", "Kate"] as user>
    ${user[1..2]}
</#list>

<#list ["星期一","星期二","星期三","星期四","星期五"] as x>
    ${x_index +1}.${x} <#if x_has_next>,</#if>
    <#if x = "星期四"><#break></#if>
</#list>

------------Map

<#assign h = {"name":"mouse", "price":50}>
<#assign w = h?keys>
<#list w as key>${key} = ${h[key]}; </#list>

<#assign h = {"语文":78,"数学":83,"Java":89} >
<#assign w = h?keys>
<#list w as key >
    ${key}--->${h[key]};
</#list>


------------调用内建函数
<#assign test="Tom & Jerry" />
${test?html}
${test?upper_case?html}


补充说明：
FreeMarker对空值的处理非常严格，FreeMarker的变量必须有值，没有被赋值的变量就会抛出异常。







