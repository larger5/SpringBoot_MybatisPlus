package com.cun.plus;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cun.plus.entity.User;
import com.cun.plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 通用 CRUD
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 1、增加 insert
     */
    @Test
    public void insertTest() {
        User user = new User();
        user.setUsername("绿茶");
        user.setPassword("lvcha");
        // 返回对数据库影响操作数：1
        Integer insert1 = userMapper.insert(user); // 非空属性才会出现在 sql 中
        // 可以立刻获取插入新记录的 id
        System.out.println(user.getId());
        // 同上
        Integer insert2 = userMapper.insertAllColumn(user); // 所有属性都会出现在 sql 中
        // 同上
        System.out.println(user.getId());
    }

    /**
     * 2、修改 update
     */
    @Test
    public void updateTest() {
        User user = new User();
        user.setId(45);
        user.setUsername("cun");
        //user.setPassword("666");
        // 返回对数据库影响操作数：1
        Integer integer1 = userMapper.updateById(user); // 属性空则不修改
        System.out.println(integer1);
        // 同上
        Integer integer2 = userMapper.updateAllColumnById(user); // 属性空则字段空
        System.out.println(integer2);
    }

    /**
     * 3、查询 select
     */
    @Test
    public void selectTest() {
        // 根据id 查询一条记录
        User user = userMapper.selectById(46);
        System.out.println(user.getUsername());

        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(61);
        idList.add(63);
        // 根据多个id 批量查询
        List<User> users = userMapper.selectBatchIds(idList);
        System.out.println(users.get(0).getUsername() + users.get(1).getUsername());

        User user1 = new User();
        user1.setId(61);
        user1.setUsername("cun");
        // 根据多个条件返回一个对象，若有多个符合条件的记录则将报错
        User user2 = userMapper.selectOne(user1);
        System.out.println(user2.getPassword());

        // 根据多个条件返回对象组，注意Map 中的key 必须和数据库表字段一直
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("username", "cun");
        List<User> users1 = userMapper.selectByMap(columnMap);
        System.out.println(users1.size());


    }

    /**
     * 4、删除 delete
     */
    @Test
    public void deleteTest() {
        // 根据一个id 删除，返回对数据库影响操作数：1
        Integer integer1 = userMapper.deleteById(65);// 根据id删除一条记录
        System.out.println(integer1);

        ArrayList<Integer> idList = new ArrayList<>(); // 根据id集合批量删除
        idList.add(64);
        idList.add(66);
        // 根据多个id 批量删除，返回对数据库影响操作数：2
        Integer integer2 = userMapper.deleteBatchIds(idList);
        System.out.println(integer2);

        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("username", "cun");
        // 根据多个条件删除，返回对数据库影响操作数
        Integer integer3 = userMapper.deleteByMap(columnMap);
        System.out.println(integer3);
    }

    /**
     * 5、伪分页（获取全部数据再分页）
     */
    @Test
    public void pageTest() {
        // 分页查询，注意如果设置了 PaginationInterceptor 分页插件则会报错，
        List<User> users2 = userMapper.selectPage(new Page<User>(1, 2), null); //当前页、每页大小
        System.out.println(users2.get(0).getUsername() + users2.get(1).getUsername());
        System.out.println(users2.size());
    }

}
