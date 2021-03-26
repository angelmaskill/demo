<?xml version="1.0" encoding="UTF-8"?>
<Root path="${path}" state="0">
    <list>
        <#list userList as user>
            <item id=${user_index+1}>
                <name>${user.name}</name>
                <age>${user.age}</age>
                <sex>${user.sex}</sex>
                <class>${user.class}</class>
            </item>
        </#list>
    </list>
</Root>