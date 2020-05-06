package com.emart.user.dao;

import com.emart.user.entity.Seller;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface SellerMapper {
    @Delete({
        "delete from seller",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into seller (id, username, ",
        "password, company_name, ",
        "GSTIN, brief_about_company, ",
        "postal_address, website, ",
        "email, contact_number, ",
        "create_datetime)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{companyName,jdbcType=VARCHAR}, ",
        "#{gstin,jdbcType=VARCHAR}, #{briefAboutCompany,jdbcType=VARCHAR}, ",
        "#{postalAddress,jdbcType=VARCHAR}, #{website,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{contactNumber,jdbcType=VARCHAR}, ",
        "#{createDatetime,jdbcType=TIMESTAMP})"
    })
    int insert(Seller record);

    @Select({
        "select",
        "id, username, password, company_name, GSTIN, brief_about_company, postal_address, ",
        "website, email, contact_number, create_datetime",
        "from seller",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_name", property="companyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="GSTIN", property="gstin", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief_about_company", property="briefAboutCompany", jdbcType=JdbcType.VARCHAR),
        @Result(column="postal_address", property="postalAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="website", property="website", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_number", property="contactNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    Seller selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, username, password, company_name, GSTIN, brief_about_company, postal_address, ",
        "website, email, contact_number, create_datetime",
        "from seller"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_name", property="companyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="GSTIN", property="gstin", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief_about_company", property="briefAboutCompany", jdbcType=JdbcType.VARCHAR),
        @Result(column="postal_address", property="postalAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="website", property="website", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_number", property="contactNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Seller> selectAll();

    @Update({
        "update seller",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "company_name = #{companyName,jdbcType=VARCHAR},",
          "GSTIN = #{gstin,jdbcType=VARCHAR},",
          "brief_about_company = #{briefAboutCompany,jdbcType=VARCHAR},",
          "postal_address = #{postalAddress,jdbcType=VARCHAR},",
          "website = #{website,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "contact_number = #{contactNumber,jdbcType=VARCHAR},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Seller record);
    
    @Select({"select * from seller where username = #{username}"})
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_name", property="companyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="GSTIN", property="gstin", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief_about_company", property="briefAboutCompany", jdbcType=JdbcType.VARCHAR),
        @Result(column="postal_address", property="postalAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="website", property="website", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_number", property="contactNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    Seller selectByName(String username);
}