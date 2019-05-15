package com.java.service;

import java.sql.SQLException;
import java.util.List;

import com.java.dao.MemberDAO;
import com.java.dto.MemberVO;
import com.java.exception.IdNotFoundException;
import com.java.exception.InvalidPasswordException;

public class MemberServiceImpl implements MemberService{

	// 싱글톤 패턴 구현
	private static MemberServiceImpl instance=new MemberServiceImpl();
	private MemberServiceImpl() {}
	public static MemberServiceImpl getInstance() {
		return instance;
	}
	
	// MemberDAO 
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO=memberDAO;
	}
	
	@Override
	public void login(String id, String pwd) throws SQLException,
												    IdNotFoundException,
												    InvalidPasswordException {
		
		MemberVO member = memberDAO.selectMemberById(id);
		
		if(member==null) throw new IdNotFoundException();
		if(!pwd.equals(member.getPwd())) throw new InvalidPasswordException();
		
	}
	@Override
	public void regist(MemberVO member) throws SQLException {
		memberDAO.insertMember(member);
		
	}
	@Override
	public MemberVO getMember(String id) throws SQLException {
		return memberDAO.selectMemberById(id);
	}
	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		
		return memberDAO.selectMemberList();
	}
	@Override
	public void modify(MemberVO member) throws SQLException {
		memberDAO.updateMember(member);		
	}
	@Override
	public void remove(String id) throws SQLException {
		memberDAO.deleteMember(id);
	}

}




