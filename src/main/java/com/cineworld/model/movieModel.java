package com.cineworld.model;

public class movieModel {
		private int movieId;
		private String movieName;
		private int movieTime;

		public movieModel() {
		}

		public movieModel(String movieName) {
			this.movieName = movieName;
		}

		public movieModel(int movieId, String movieName, int movieTime) {
			super();
			this.movieId = movieId;
			this.movieName = movieName;
			this.movieTime = movieTime;
		}

		public int getMovieId() {
			return movieId;
		}

		public void setMovieId(int movieId) {
			this.movieId = movieId;
		}

		public String getMovieName() {
			return movieName;
		}

		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}

		public int getMovieTime() {
			return movieTime;
		}

		public void setMovieTime(int movieTime) {
			this.movieTime = movieTime;
		}
}

