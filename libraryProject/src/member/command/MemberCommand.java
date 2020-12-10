package member.command;

import javax.servlet.http.*;

import member.dao.*;
import member.vo.*;

public class MemberCommand {
	MemberDAO memberDAO;
	MemberVO memberVO;
	
	public MemberCommand() {
		memberDAO = new MemberDAO();
		memberVO = new MemberVO();
	}
	
	public void memberJoin(MemberVO memberVO) {
		memberDAO.memberJoin(memberVO);
	}
	
	public MemberVO login_chk(String user_id, String user_pw) {
		memberVO = memberDAO.login_chk(user_id, user_pw);
		return memberVO;
	}
	
	//아이디 중복 확인
	public boolean id_chk(String id) {
		boolean result = memberDAO.id_chk(id);
		return result;
	}
	
	//정보 수정시 비밀번호 재확인
	public boolean pw_chk(String id, String pw) {
		boolean result = memberDAO.pw_chk(id, pw);
		return result;
	}
	
	public void memberInfoModify(MemberVO vo) {
		memberDAO.memberInfoModify(vo);
	}
}
