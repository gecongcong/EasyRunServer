package gcc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import gcc.po.UserBean;


public class UserDao
{
	Connection conn = null;

	public UserDao(Connection c)
	{
		conn = c;
	}
	
	public UserBean findUser(String account, String password) throws SQLException
	{
		final String sql1 = "select * from Users where Account=? and Password=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, account);
			ps1.setString(2, password);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next())// no match
				return null;

			// get user
			UserBean user = new UserBean();
			user.setUserID(rs1.getString("UserID"));
			/*user.setUserName(rs1.getString("HeadImgUrl"));
			user.setUserName(rs1.getString("RealName"));
			user.setCelphone(rs1.getString("Celphone"));
			user.setEmail(rs1.getString("Email"));
			user.setBirth(rs1.getDate("Birth"));
			user.setGender(rs1.getInt("Gender"));
			user.setIdentityCard(rs1.getString("IdentityCard"));
			user.setIdentityPic(rs1.getString("IdentityPic"));
			user.setBloodType(rs1.getString("BloodType"));
			user.setAddress(rs1.getString("Address"));
			user.setHeight(rs1.getFloat("Height"));
			user.setWeight(rs1.getFloat("Weight"));
			user.setUrgencyContact(rs1.getString("UrgencyContact"));
			user.setUrgencyPhone(rs1.getString("UrgencyPhone"));*/
			
			return user;
		}
	}

	public boolean AddBaseUser(UserBean user)
	{
		final String sql = "insert into Users (UserID,Account,Password,IdentityPic) values(?,?,?,?)";
		try (PreparedStatement ps1 = conn.prepareStatement(sql))
		{
			String UserID = UUID.randomUUID().toString();//长度为36位的字符串
			ps1.setString(1, UserID);
			ps1.setString(2, user.getAccount());
			ps1.setString(3, user.getPassword());
			ps1.setString(4, user.getIdentityPic());
			ps1.executeUpdate();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public UserBean UpdUser(UserBean user) throws SQLException
	{
		final String sql1 = "update Users set UserName=?,Celphone=?,Email=?,IdentityCard=?,"
				+ "IdentityPic=?,BloodType=?,Address=?,Height=?,Weight=?,UrgencyContact=?,UrgencyPhone=?"
				+ " where UserID=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, user.getUserName());
			ps1.setString(2, user.getCelphone());
			ps1.setString(3, user.getEmail());
			ps1.setString(4, user.getIdentityCard());
			ps1.setString(5, user.getIdentityPic());
			ps1.setString(6, user.getBloodType());
			ps1.setString(7, user.getAddress());
			ps1.setFloat(8, user.getHeight());
			ps1.setFloat(9, user.getWeight());
			ps1.setString(10, user.getUrgencyContact());
			ps1.setString(11, user.getUrgencyPhone());
			ps1.setString(12, user.getUserID());
			
			ps1.executeUpdate();
			return user;
		}
	}
	
	public boolean registerUpdate(UserBean user)
	{//注册时详细信息写入数据库
		final String sql1 = "update Users set UserName=?,Email=?"
						  + " where Account=? and Password=?";
		try (PreparedStatement ps1 = conn.prepareStatement(sql1))
		{
			ps1.setString(1, user.getUserName());
			ps1.setString(2, user.getEmail());
			ps1.setString(3, user.getAccount());
			ps1.setString(4, user.getPassword());
			ps1.executeUpdate();
			return true;
		}catch(SQLException e){
			return false;
		}
	}
}
