package com.xinnuo.se.util.dtsql;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * todo: description
 *
 * @author yiding.he
 */
public class SQLTest {

    private static void print(SQL.Generatable generatable) {
        Command command = generatable.toCommand();
        System.out.println(command.getStatement());
        System.out.println(command.getParams());
        System.out.println();
    }

    @Test
    public void testSelect() throws Exception {
        String usernamePrefix = "JOHN%";
        String role = "admin";
        String username = "admin";

        print(
                SQL.Select("*").From("USERS")
                        .Where("USERNAME like ?", usernamePrefix)
                        .And("(ROLE=?", role).Or("USERNAME=?)", username)
        );

        print(
                SQL.Select("*").From("USERS")
                        .Where("USERNAME like ?", usernamePrefix)
                        .Append("and (")
                        .Append(role != null, "ROLE=?", role)
                        .Or("USERNAME=?", username)
                        .Append(")")
        );

        role = null;
        print(
                SQL.Select("count(1), ROLE")
                        .From("USERS")
                        .Where(false, "REGISTER_TIME>sysdate-1")    // dismissed
                        .AndIfNotEmpty("ROLE=?", role)          // dismissed
                        .And("1=1")
                        .GroupBy("ROLE")
        );

        List<Integer> levels = Arrays.asList(1, 2, 3);
        print(
                SQL.Select("*")
                        .From("USERS")
                        .Where("USER_LEVEL in ?", levels)
        );

    }

    @Test
    public void testUpdate() throws Exception {

        print(
                SQL.Update("USER")
                        .Set("AGE", 3)
                        .Set(false, "NAME", "admin").Where("ID=?", 1)
        );
    }

    @Test
    public void testInsert() throws Exception {
        print(
                SQL.Insert("USER")
                        .Values("ID", 1)
                        .Values("USERNAME", "admin")
                        .Values("PASSWORD", "admin")
                        .Values("AGE", null)
        );
    }

    @Test
    public void testInsertWhere() throws Exception {
        // SQL.Insert("USER").Values("NAME", "admin").Where(""); // 将抛出异常
    }

    @Test
    public void testDelete() throws Exception {
        print(
                SQL.Delete("USER").Where("ID in ?", Arrays.asList(1, 2, 3, 4, 5))
        );
    }
}
