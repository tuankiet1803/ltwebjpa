package ltwebjpa.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name ="Username")
	private String username;
	@Column(name="Active")
	private boolean active;
	@Column(name="Admin")
	private boolean admin;
	@Column(name="Email")
	@Email(message = "Hay nhap dung dinh dang emaail")
	@NotEmpty(message = "Hay nhap email")
	private String email;
	@Column(name="Phone")
	@Pattern(regexp = "^\\d{8,10}$", message = "So dien thoai tu 8 den 10 so")
	@NotEmpty(message = "Hay nhap so dien thoai")
	private String phone;
	@Column(name="Fullname", columnDefinition = "nvarchar(50)")
	private String fullname;
	@Column(name="Passwrod")
	private String password;
	@Column(name="Images")
	private String images;
}
