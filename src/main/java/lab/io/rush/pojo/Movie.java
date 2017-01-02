package lab.io.rush.pojo;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table="movie")
public class Movie {

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Integer id;
	
	@Column(name="movieName")
	private String movieName;
	
	@Column(name="movieNum")
	private Integer movieNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Integer getMovieNum() {
		return movieNum;
	}

	public void setMovieNum(Integer movieNum) {
		this.movieNum = movieNum;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieName=" + movieName + ", movieNum=" + movieNum + "]";
	}
	
	
	
}
