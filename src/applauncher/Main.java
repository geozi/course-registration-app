package applauncher;

import java.awt.EventQueue;

import admin.AdminMenu;
import admin.CourseInsertForm;
import admin.CourseSearchForm;
import admin.CourseUpdateDeleteForm;
import admin.StudentInsertForm;
import admin.StudentSearchForm;
import admin.StudentUpdateDeleteForm;
import admin.TeacherInsertForm;
import admin.TeacherSearchForm;
import admin.TeacherUpdateDeleteForm;
import admin.UserSearchForm;
import user.CourseRegistrationForm;
import user.UpdatePasswordForm;
import user.UserMenu;
import admin.UserDeleteForm;

/**
 * The {@link Main} class is the driver class of the
 * application. Its primary responsibility is to load
 * all forms into memory and provide access points to 
 * their addresses.
 */
public class Main {
	private static LoginRegisterForm loginRegisterForm;
	private static AdminMenu adminMenu;
	private static UserMenu userMenu;
	private static CourseSearchForm courseSearchForm;
	private static StudentSearchForm studentSearchForm;
	private static TeacherSearchForm teacherSearchForm;
	private static UserSearchForm userSearchForm;
	private static CourseUpdateDeleteForm courseUpdateDeleteForm;
	private static StudentUpdateDeleteForm studentUpdateDeleteForm;
	private static TeacherUpdateDeleteForm teacherUpdateDeleteForm;
	private static UserDeleteForm userDeleteForm;
	private static TeacherInsertForm teacherInsertForm;
	private static StudentInsertForm studentInsertForm;
	private static CourseInsertForm courseInsertForm;
	private static CourseRegistrationForm courseRegistrationForm;
	private static UpdatePasswordForm updatePasswordForm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					
					loginRegisterForm = new LoginRegisterForm();
					loginRegisterForm.setVisible(true);
					
					adminMenu = new AdminMenu();
					adminMenu.setVisible(false);
					
					userMenu = new UserMenu();
					userMenu.setVisible(false);

					courseSearchForm = new CourseSearchForm();
					courseSearchForm.setVisible(false);

					studentSearchForm = new StudentSearchForm();
					studentSearchForm.setVisible(false);

					teacherSearchForm = new TeacherSearchForm();
					teacherSearchForm.setVisible(false);

					userSearchForm = new UserSearchForm();
					userSearchForm.setVisible(false);

					courseUpdateDeleteForm = new CourseUpdateDeleteForm();
					courseUpdateDeleteForm.setVisible(false);

					studentUpdateDeleteForm = new StudentUpdateDeleteForm();
					studentUpdateDeleteForm.setVisible(false);

					teacherUpdateDeleteForm = new TeacherUpdateDeleteForm();
					teacherUpdateDeleteForm.setVisible(false);
					
					userDeleteForm = new UserDeleteForm();
					userDeleteForm.setVisible(false);
					
					teacherInsertForm = new TeacherInsertForm();
					teacherInsertForm.setVisible(false);
					
					studentInsertForm = new StudentInsertForm();
					studentInsertForm.setVisible(false);
					
					courseInsertForm = new CourseInsertForm();
					courseInsertForm.setVisible(false);
					
					courseRegistrationForm = new CourseRegistrationForm();
					courseRegistrationForm.setVisible(false);
					
					updatePasswordForm = new UpdatePasswordForm();
					updatePasswordForm.setVisible(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}
	
	
	public static LoginRegisterForm getLoginRegisterForm() {
		return loginRegisterForm;
	}

	public static AdminMenu getAdminMenu() {
		return adminMenu;
	}
	
	public static UserMenu getUserMenu() {
		return userMenu;
	}
	
	public static CourseSearchForm getCourseSearchForm() {
		return courseSearchForm;
	}

	public static StudentSearchForm getStudentSearchForm() {
		return studentSearchForm;
	}

	public static TeacherSearchForm getTeacherSearchForm() {
		return teacherSearchForm;
	}

	public static UserSearchForm getUserSearchForm() {
		return userSearchForm;
	}

	public static CourseUpdateDeleteForm getCourseUpdateDeleteForm() {
		return courseUpdateDeleteForm;
	}

	public static StudentUpdateDeleteForm getStudentUpdateDeleteForm() {
		return studentUpdateDeleteForm;
	}

	public static TeacherUpdateDeleteForm getTeacherUpdateDeleteForm() {
		return teacherUpdateDeleteForm;
	}

	public static UserDeleteForm getUserDeleteForm() {
		return userDeleteForm;
	}

	public static TeacherInsertForm getTeacherInsertForm() {
		return teacherInsertForm;
	}

	public static StudentInsertForm getStudentInsertForm() {
		return studentInsertForm;
	}

	public static CourseInsertForm getCourseInsertForm() {
		return courseInsertForm;
	}

	public static CourseRegistrationForm getCourseRegistrationForm() {
		return courseRegistrationForm;
	}

	public static UpdatePasswordForm getUpdatePasswordForm() {
		return updatePasswordForm;
	}
	
	
}
