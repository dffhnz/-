package com.daojucheng.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.daojucheng.dao.Basedao;
import com.daojucheng.entity.DAOJUCHENG_CATEGORY;
import com.daojucheng.entity.DAOJUCHENG_ITEM;
import com.daojucheng.entity.DAOJUCHENG_SHOPPING;
import com.daojucheng.entity.DAOJUCHENG_USER;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class DaoJuChengDao {
	public static int insert(DAOJUCHENG_USER u){
		
			String sql="insert into daojucheng_user values(?,?,?,?,?,?)";
			
			Object[] params= {
					u.getUser_id(),
					u.getUser_name(),
					u.getUser_password(),
					u.getUser_sex(),
					u.getUser_status(),
					u.getUser_gameid()
			};
			
			
			return Basedao.exectuIUD(sql, params);
		}
	public static int update(DAOJUCHENG_USER u){
		
		String sql="update  daojucheng_user set user_name=?,user_password=?,user_sex=?,USER_STATUS=? ,user_gameid=? where USER_ID=?";
		
		Object[] params= {
				u.getUser_name(),
				u.getUser_password(),
				u.getUser_sex(),
				u.getUser_status(),
				u.getUser_gameid(),
				u.getUser_id()
		};
		
		
		return Basedao.exectuIUD(sql, params);
	}
	//获取用户总记录数和总页数
	public static int[] totalpage(int count,String keyword) {
		int arr[]= {0,1};
		//0为总记录是 1是总页数
		Connection conn=Basedao.getconn();
		PreparedStatement ps= null;
		ResultSet rs=null;
		try {
			if(keyword!=null) {
				String sql="select count(*) from daojucheng_user WHERE user_name like ?";
				ps=conn.prepareStatement(sql);
				ps.setString(1,"%"+keyword+"%");
			}else {
				String sql="select count(*) from daojucheng_user";
				ps=conn.prepareStatement(sql);
			}

			
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				arr[0] = rs.getInt(1);		
				if((arr[0]%count)==0) {
					arr[1]=arr[0]/count;
				}else {
					arr[1]=arr[0]/count+1;
				}
			}
			if(arr[0]==0)
				arr[1]=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return arr;
	}
	//查询用户
	public static int selectByName(String id) {
		Connection conn=Basedao.getconn();
		PreparedStatement ps= null;
		ResultSet rs=null;
		int count=0;
		try {
				String sql="select count(*) from daojucheng_user WHERE user_id=? ";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
			    rs=ps.executeQuery();
			
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return count;
	}
	//删除用户
	public static int delete(String id) {
		String sql="delete from daojucheng_user where user_id=?";
		
		Object[] params= {id};
		
		return Basedao.exectuIUD(sql, params);
		
	}
	//查询所有用户
	public static ArrayList<DAOJUCHENG_USER>selectAll(int cpage, int count,String keyword){
		ArrayList<DAOJUCHENG_USER> list=new ArrayList<DAOJUCHENG_USER>();
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			if(keyword!=null) {
				String sql="select * from daojucheng_user where user_name like ? order by user_status desc limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setString(1,"%"+keyword+"%");
				ps.setInt(2,(cpage-1)*count);
				ps.setInt(3,count);
			}else {
				String sql="select * from daojucheng_user order by user_status desc limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, (cpage-1)*count);
				ps.setInt(2, count);
			}
			rs=ps.executeQuery();
			
			while (rs.next()) {
				DAOJUCHENG_USER u=new DAOJUCHENG_USER(rs.getString("user_id"),
						rs.getString("user_name"),
						rs.getString("user_password"),
						rs.getString("user_sex"),
						rs.getInt("user_status"),
						rs.getInt("user_gameid")
				);
				
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	//通过ID查找用户
	public static DAOJUCHENG_USER selectByID(String id){
		DAOJUCHENG_USER u=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from daojucheng_user  where user_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				 u=new DAOJUCHENG_USER(rs.getString("user_id"),
									rs.getString("user_name"),
									rs.getString("user_password"),
									rs.getString("user_sex"),
									rs.getInt("user_status"),
						 			rs.getInt("user_gameid"));
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return u;
	}
	//用户登录，根据用户名和密码登录，查询sql
	public  static int selectByNamePassWord(String id,String password){
		Connection conn=Basedao.getconn();
		PreparedStatement ps= null;
		ResultSet rs=null;
		int count=0;
		try {
			String sql="select count(*) from daojucheng_user WHERE user_id=? and user_password=? ";
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,password);
			rs=ps.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null){
				try {
					rs.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
			if (ps != null){
				try {
					ps.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
			Basedao.closeall(rs, ps, conn);
		}
		return count;
	}
	//更改用户的密码
	public static int userupdatepassword(DAOJUCHENG_USER u) {
		String sql = "update  daojucheng_user set user_password=? where user_name=?";
		Object[] params = {
				u.getUser_password(),
				u.getUser_name()
		};
		return Basedao.exectuIUD(sql, params);
	}
//	public static int selectByNM(String name,String password){
//		int count=0;
//		Connection conn=Basedao.getconn();
//		PreparedStatement ps= null;
//		ResultSet rs=null;
//		try {
//				String sql="select count(*) from LMONKEY_USER WHERE USER_ID=? and USER_PASSWORD=? ";
//				ps=conn.prepareStatement(sql);
//				ps.setString(1, name);
//				ps.setString(2, password);
//			    rs=ps.executeQuery();
//			
//			while(rs.next()) {
//				count=rs.getInt(1);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			Basedao.closeall(rs, ps, conn);
//		}
//		
//		return count;
//	}


	//获取商品总记录数和总页数
	public static int[] itemtotalpage(int count,String keyword,int categoryy) {
		int arr[]= {0,1};
		//0为总记录是 1是总页数
		Connection conn=Basedao.getconn();
		PreparedStatement ps= null;
		ResultSet rs=null;
		try {
			if(keyword!=null&& categoryy==0) {
				String sql="select * from item where name like ? ";
				ps=conn.prepareStatement(sql);
				ps.setString(1,"%"+keyword+"%");
			}else if(keyword==null && categoryy!=0) {
				String sql="select * from item where categoryId=? ";
				ps=conn.prepareStatement(sql);
				ps.setInt(1,categoryy);
			}else if(keyword!=null && categoryy!=0){
				String sql="select * from item where name like ? and categoryId=?  ";
				ps=conn.prepareStatement(sql);
				ps.setString(1,"%"+keyword+"%");
				ps.setInt(2,categoryy);
			} else{
				String sql="select * from item  ";
				ps=conn.prepareStatement(sql);
			}


			rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
			}
			arr[0] = i;
			if((arr[0]%count)==0) {
				arr[1]=arr[0]/count;
			}else {
				arr[1]=arr[0]/count+1;
			}
			if(arr[0]==0)
				arr[1]=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return arr;
	}
	//查询所有商品
	public static ArrayList<DAOJUCHENG_ITEM>selectITEMAll(int cpage, int count, String keyword,int categoryy){
		ArrayList<DAOJUCHENG_ITEM> list=new ArrayList<DAOJUCHENG_ITEM>();
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			if(keyword!=null&& categoryy==0) {
				String sql="select * from item where name like ? order by id asc limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setString(1,"%"+keyword+"%");
				ps.setInt(2,(cpage-1)*count);
				ps.setInt(3,count);
			}else if(keyword==null && categoryy!=0) {
				String sql="select * from item where categoryId=? order by id asc limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1,categoryy);
				ps.setInt(2,(cpage-1)*count);
				ps.setInt(3,count);
			}else if(keyword!=null && categoryy!=0){
				String sql="select * from item where name like ? and categoryId=?  order by id asc limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setString(1,"%"+keyword+"%");
				ps.setInt(2,categoryy);
				ps.setInt(3,(cpage-1)*count);
				ps.setInt(4,count);

			} else{
				String sql="select * from item order by id asc limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, (cpage-1)*count);
				ps.setInt(2, count);
			}
			rs=ps.executeQuery();

			while (rs.next()) {
				DAOJUCHENG_ITEM i=new DAOJUCHENG_ITEM(rs.getInt("id"),
						rs.getInt("categoryId"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("itemdesc"),
						rs.getInt("inventory")
				);

				list.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	//查询所有商品
	public static ArrayList<DAOJUCHENG_ITEM>selectitemAll(){
		ArrayList<DAOJUCHENG_ITEM> list=new ArrayList<DAOJUCHENG_ITEM>();
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {

				String sql="select * from item where categoryId=1 order by inventory desc limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, 0);
				ps.setInt(2, 11);
			rs=ps.executeQuery();

			while (rs.next()) {
				DAOJUCHENG_ITEM i=new DAOJUCHENG_ITEM(rs.getInt("id"),
						rs.getInt("categoryId"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("itemdesc"),
						rs.getInt("inventory")
				);

				list.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	//查询商品详情
	public static DAOJUCHENG_ITEM selectItemId(int id) {
		DAOJUCHENG_ITEM i=null;
		Connection conn=Basedao.getconn();
		PreparedStatement ps= null;
		ResultSet rs=null;
		try {
			String sql="select * from item WHERE id=? ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();

			while(rs.next()) {
				DAOJUCHENG_ITEM ii=new DAOJUCHENG_ITEM(rs.getInt("id"),
						rs.getInt("categoryId"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("itemdesc"),
						rs.getInt("inventory"));
				i=ii;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return i;
	}
	//添加商品
	public static int itemadd(DAOJUCHENG_ITEM i){

		String sql="insert into item values(null,?,?,?,?,?,?)";
		Object[] params= {i.getCategoryId(),
				i.getName(),
				i.getPrice(),
				i.getImage(),
				i.getItemdesc(),
				i.getInventory()
		};
		return Basedao.exectuIUD(sql, params);
	}
	//删除商品
	public static int itemdelete(String id) {
		String sql="delete from item where id=?";

		Object[] params= {id};

		return Basedao.exectuIUD(sql, params);
	}

	//通过ID查找商品
	public static DAOJUCHENG_ITEM selectByitemID(String id){
		DAOJUCHENG_ITEM i=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from item where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);

			rs=ps.executeQuery();

			while (rs.next()) {
				i=new DAOJUCHENG_ITEM(rs.getInt("id"),
						rs.getInt("categoryId"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("image"),
						rs.getString("itemdesc"),
						rs.getInt("inventory"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return i;
	}
	//修改订单
	public static int itemupdate(DAOJUCHENG_ITEM i){

		String sql="update  item set categoryId=?,name=?,price=?,image=?,itemdesc=?,inventory=? where id=?";

		Object[] params= {
				i.getCategoryId(),
				i.getName(),
				i.getPrice(),
				i.getImage(),
				i.getItemdesc(),
				i.getInventory(),
				i.getId()
		};


		return Basedao.exectuIUD(sql, params);
	}


	//查询所有商品分类
	public static ArrayList<DAOJUCHENG_CATEGORY>selectCategoryAll(){
		ArrayList<DAOJUCHENG_CATEGORY> list=new ArrayList<DAOJUCHENG_CATEGORY>();
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from category";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();

			while (rs.next()) {
				DAOJUCHENG_CATEGORY c=new DAOJUCHENG_CATEGORY(rs.getInt("id"),
						rs.getString("name"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	//获取商品分类总记录数和总页数
	public static int[] categorytotalpage(int count) {
		int arr[]= {0,1};
		//0为总记录是 1是总页数
		Connection conn=Basedao.getconn();
		PreparedStatement ps= null;
		ResultSet rs=null;
		try {

			String sql="select * from category ";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
			}
			arr[0] = i;
			if((arr[0]%count)==0) {
				arr[1]=arr[0]/count;
			}else {
				arr[1]=arr[0]/count+1;
			}
			if(arr[0]==0)
				arr[1]=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return arr;
	}
	//添加商品分类
	public static int categoryadd(DAOJUCHENG_CATEGORY c){

		String sql="insert into category values(null,?)";
		Object[] params= {c.getName()};
		return Basedao.exectuIUD(sql, params);
	}
	//删除分类
	public static int categorydelete(String id) {
		String sql="delete from category where id=?";

		Object[] params= {id};

		return Basedao.exectuIUD(sql, params);
	}
	//修改分类
	public static int categoryupdate(DAOJUCHENG_CATEGORY u){

		String sql="update  category set name=? where id=?";

		Object[] params= {
				u.getName(),
				u.getId()
		};


		return Basedao.exectuIUD(sql, params);
	}
	//通过ID查找分类
	public static DAOJUCHENG_CATEGORY selectByCategoryID(String id){
		DAOJUCHENG_CATEGORY c=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from category where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);

			rs=ps.executeQuery();

			while (rs.next()) {
				c=new DAOJUCHENG_CATEGORY(rs.getInt("id"),
						rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return c;
	}
	//通过ID查找用户
	public static String nameByCategoryID(String id){
		String n=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from category where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);

			rs=ps.executeQuery();

			while (rs.next()) {
				n= rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return n;
	}



	//通过用户ID查找用户的购物车
	public static ArrayList<DAOJUCHENG_SHOPPING> shoppingselect(String uid){
		ArrayList<DAOJUCHENG_SHOPPING> list=new ArrayList<DAOJUCHENG_SHOPPING>();
		DAOJUCHENG_SHOPPING s=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		try {
			String sql="select * from shopping  where uid=? and status=1";
			ps=conn.prepareStatement(sql);
			ps.setString(1,uid);

			rs=ps.executeQuery();

			while (rs.next()) {
				s=new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						DaoJuChengDao.nameByCategoryID(rs.getString("category")),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	//通过UID和IID查看购物车中是否有该商品
	public static int selectByitemIDandByuserID(int iid,String uid){
		int count=0;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from shopping where uid=? and iid=? and status=1";
			ps=conn.prepareStatement(sql);
			ps.setString(1,uid);
			ps.setInt(2,iid);

			rs=ps.executeQuery();

			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return count;
	}
	//通过UID和IID查看购物车中是否有该商品
	public static DAOJUCHENG_SHOPPING shoppingByitemIDandByuserID(int iid,String uid){
		DAOJUCHENG_SHOPPING s=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from shopping where uid=? and iid=? and status=1";
			ps=conn.prepareStatement(sql);
			ps.setString(1,uid);
			ps.setInt(2,iid);

			rs=ps.executeQuery();

			while (rs.next()) {
				s=new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						rs.getString("category"),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return s;
	}
	//购物车添加商品
	public static int shoppingadd(DAOJUCHENG_SHOPPING s){

		String sql="insert into shopping values(null,?,?,?,?,?,?,?,1)";

		Object[] params= {
				s.getCategory(),
				s.getIid(),
				s.getName(),
				s.getImage(),
				s.getPrice(),
				s.getUid(),
				s.getNum()
		};
		return Basedao.exectuIUD(sql, params);
	}
	//购物车添加商品2(只加数量)
	public static int shoppingupdate(DAOJUCHENG_SHOPPING s){

		String sql="update shopping set category=?,iid=?,name=?,image=?,price=?,uid=?,num=? where id=?";

		Object[] params= {
				s.getCategory(),
				s.getIid(),
				s.getName(),
				s.getImage(),
				s.getPrice(),
				s.getUid(),
				s.getNum(),
				s.getId()
		};
		return Basedao.exectuIUD(sql, params);
	}
	//删除购物车商品
	public static int shoppingdelete(int id) {
		String sql = "delete from shopping where id=? and status=1";

		Object[] params = {id};
		return Basedao.exectuIUD(sql, params);
	}
	//获取订单总记录数和总页数
	public static int[] ordertotalpage(int count) {
		int arr[]= {0,1};
		//0为总记录是 1是总页数
		Connection conn=Basedao.getconn();
		PreparedStatement ps= null;
		ResultSet rs=null;
		try {

			String sql="select * from shopping where status=2";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
			}
			arr[0] = i;
			if((arr[0]%count)==0) {
				arr[1]=arr[0]/count;
			}else {
				arr[1]=arr[0]/count+1;
			}
			if(arr[0]==0)
				arr[1]=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return arr;
	}
	//查询所有用户的订单
	public static ArrayList<DAOJUCHENG_SHOPPING> selectorderAll(int cpage, int count){
		ArrayList<DAOJUCHENG_SHOPPING> list=new ArrayList<DAOJUCHENG_SHOPPING>();
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
				String sql="select * from shopping where status=2 order by status asc limit ?,? ";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, (cpage-1)*count);
				ps.setInt(2, count);
				rs=ps.executeQuery();

			while (rs.next()) {
				DAOJUCHENG_SHOPPING s=new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						rs.getString("category"),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	//查询当前用户的订单
	public static ArrayList<DAOJUCHENG_SHOPPING> userselectorderAll(String uid){
		ArrayList<DAOJUCHENG_SHOPPING> list=new ArrayList<DAOJUCHENG_SHOPPING>();
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from shopping where status=2 or status=3 and uid=? ";
			ps=conn.prepareStatement(sql);
			ps.setString(1,uid);
			rs=ps.executeQuery();

			while (rs.next()) {
				DAOJUCHENG_SHOPPING s=new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						rs.getString("category"),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
	//通过订单ID查找订单详情修改分类
	public static DAOJUCHENG_SHOPPING orderselect(int id){
		DAOJUCHENG_SHOPPING s=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		try {
			String sql="select * from shopping  where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			rs=ps.executeQuery();

			while (rs.next()) {
				s=new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						DaoJuChengDao.nameByCategoryID(rs.getString("category")),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return s;
	}
	//通过订单ID查找订单详情不修改分类
	public static DAOJUCHENG_SHOPPING orderselect1(int id){
		DAOJUCHENG_SHOPPING s=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		try {
			String sql="select * from shopping  where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			rs=ps.executeQuery();

			while (rs.next()) {
				s=new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						rs.getString("category"),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return s;
	}
	//处理订单
	public static int orderupdate(DAOJUCHENG_SHOPPING s){

		String sql="update shopping set category=?,iid=?,name=?,image=?,price=?,uid=?,num=?,status=3 where id=?";

		Object[] params= {
				s.getCategory(),
				s.getIid(),
				s.getName(),
				s.getImage(),
				s.getPrice(),
				s.getUid(),
				s.getNum(),
				s.getId()
		};
		return Basedao.exectuIUD(sql, params);
	}
	//通过ID获取购物车中商品
	public static DAOJUCHENG_SHOPPING shoppingByitemID(int id){
		DAOJUCHENG_SHOPPING s=null;
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();

		PreparedStatement ps=null;
		try {
			String sql="select * from shopping where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);

			rs=ps.executeQuery();

			while (rs.next()) {
				s=new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						rs.getString("category"),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return s;
	}
	//提交订单
	public static int shoppingsubmit(DAOJUCHENG_SHOPPING s){

		String sql="update shopping set category=?,iid=?,name=?,image=?,price=?,uid=?,num=?,status=2 where id=?";

		Object[] params= {
				s.getCategory(),
				s.getIid(),
				s.getName(),
				s.getImage(),
				s.getPrice(),
				s.getUid(),
				s.getNum(),
				s.getId()
		};
		return Basedao.exectuIUD(sql, params);
	}
	//确认收货
	public static int shoppinggoods(DAOJUCHENG_SHOPPING s){

		String sql="update shopping set category=?,iid=?,name=?,image=?,price=?,uid=?,num=?,status=4 where id=?";

		Object[] params= {
				s.getCategory(),
				s.getIid(),
				s.getName(),
				s.getImage(),
				s.getPrice(),
				s.getUid(),
				s.getNum(),
				s.getId()
		};
		return Basedao.exectuIUD(sql, params);
	}
	//查询当前用户购物历史的订单
	public static ArrayList<DAOJUCHENG_SHOPPING> userhistoryorderAll(String uid) {
		ArrayList<DAOJUCHENG_SHOPPING> list = new ArrayList<DAOJUCHENG_SHOPPING>();
		DAOJUCHENG_SHOPPING s = null;
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		try {
			String sql = "select * from shopping  where uid=? and status=4";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);

			rs = ps.executeQuery();

			while (rs.next()) {
				s = new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						DaoJuChengDao.nameByCategoryID(rs.getString("category")),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}//获取购物历史总记录数和总页数
	public static int[] historytotalpage(int count) {
		int arr[]= {0,1};
		//0为总记录是 1是总页数
		Connection conn=Basedao.getconn();
		PreparedStatement ps= null;
		ResultSet rs=null;
		try {
			String sql="select count(*) from shopping where status=4";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				arr[0] = rs.getInt(1);
				if((arr[0]%count)==0) {
					arr[1]=arr[0]/count;
				}else {
					arr[1]=arr[0]/count+1;
				}
			}
			if(arr[0]==0)
				arr[1]=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return arr;
	}
	//获取历史购物商品分类总记录数和总页数
	public static ArrayList<DAOJUCHENG_SHOPPING> shoppinghistorytotalpage(int cpage, int count){
		int arr[]= {0,1};
		//0为总记录是 1是总页数
		ArrayList<DAOJUCHENG_SHOPPING> list=new ArrayList<DAOJUCHENG_SHOPPING>();
		//声明结果集
		ResultSet rs=null;
		//获取连接对象
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		try {
			String sql="select * from shopping where status=4 order by status asc limit ?,? ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, (cpage-1)*count);
			ps.setInt(2, count);
			rs=ps.executeQuery();
			while (rs.next()) {
				DAOJUCHENG_SHOPPING s=new DAOJUCHENG_SHOPPING(rs.getInt("id"),
						rs.getString("category"),
						rs.getInt("iid"),
						rs.getString("name"),
						rs.getString("image"),
						rs.getInt("price"),
						rs.getString("uid"),
						rs.getInt("num"),
						rs.getInt("status"));
				arr[0] = rs.getInt(1);
				if((arr[0]%count)==0) {
					arr[1]=arr[0]/count;
				}else {
					arr[1]=arr[0]/count+1;
				}
				list.add(s);
			}
			if(arr[0]==0)
				arr[1]=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return list;
	}
}
