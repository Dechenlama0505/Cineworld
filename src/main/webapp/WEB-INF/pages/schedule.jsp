<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/schedule.css">

<div class="schedule-container">
    
    <!-- First row of movies -->
    <div class="movie-row">
        
        <!-- Dune Movie Card -->
        <div class="movie-card">
            <div class="movie-info">
                <div class="movie-poster">
                    <img src="${pageContext.request.contextPath}/images/dune.jpg" alt="Dune">
                </div>
                <div class="movie-details">
                    <h2>Dune</h2>
                    <p class="rating">80% liked this film</p>
                    <p class="description">
                        Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to a dangerous planet to ensure the future of his family and people.
                    </p>
                    <div class="showtimes">
                        <div class="day"><h3>Sunday</h3><div class="time-slot">01:00PM</div><div class="time-slot">04:00PM</div></div>
                        <div class="day"><h3>Monday</h3><div class="time-slot">01:00PM</div></div>
                        <div class="day"><h3>Tuesday</h3><div class="time-slot">03:00PM</div></div>
                    </div>
                    <a href="${pageContext.request.contextPath}/book" class="book-btn">Book</a>
                </div>
            </div>
        </div>

        <!-- Inception Movie Card -->
        <div class="movie-card">
            <div class="movie-info">
                <div class="movie-poster">
                    <img src="${pageContext.request.contextPath}/images/inception.jpg" alt="Inception">
                </div>
                <div class="movie-details">
                    <h2>Inception</h2>
                    <p class="rating">90% liked this film</p>
                    <p class="description">
                        Cobb steals information from targets by entering their dreams. His only chance at redemption is to perform a nearly impossible task: inception.
                    </p>
                    <div class="showtimes">
                        <div class="day"><h3>Sunday</h3><div class="time-slot">01:00PM</div><div class="time-slot">04:00PM</div></div>
                        <div class="day"><h3>Monday</h3><div class="time-slot">01:00PM</div></div>
                        <div class="day"><h3>Tuesday</h3><div class="time-slot">03:00PM</div></div>
                    </div>
                    <a href="${pageContext.request.contextPath}/book" class="book-btn">Book</a>
                </div>
            </div>
        </div>

    </div> <!-- End of first row -->

    <!-- Second row of movies -->
    <div class="movie-row">
        
        <!-- Eternals Movie Card -->
        <div class="movie-card">
            <div class="movie-info">
                <div class="movie-poster">
                    <img src="${pageContext.request.contextPath}/images/eternals.jpg" alt="Eternals">
                </div>
                <div class="movie-details">
                    <h2>Eternals</h2>
                    <p class="rating">65% liked this film</p>
                    <p class="description">
                        The Eternals, a race of immortal beings with superhuman powers, reunite to battle the evil Deviants after thousands of years in hiding.
                    </p>
                    <div class="showtimes">
                        <div class="day"><h3>Sunday</h3><div class="time-slot">01:00PM</div><div class="time-slot">04:00PM</div></div>
                        <div class="day"><h3>Monday</h3><div class="time-slot">01:00PM</div></div>
                        <div class="day"><h3>Tuesday</h3><div class="time-slot">03:00PM</div></div>
                    </div>
                    <a href="${pageContext.request.contextPath}/book" class="book-btn">Book</a>
                </div>
            </div>
        </div>

        <!-- Crazy Rich Asians Movie Card -->
        <div class="movie-card">
            <div class="movie-info">
                <div class="movie-poster">
                    <img src="${pageContext.request.contextPath}/images/crazy-rich-asians.jpg" alt="Crazy Rich Asians">
                </div>
                <div class="movie-details">
                    <h2>Crazy Rich Asians</h2>
                    <p class="rating">90% liked this film</p>
                    <p class="description">
                        Rachel finds out her boyfriend Nick belongs to one of the richest families in Singapore, leading to tension and unexpected challenges.
                    </p>
                    <div class="showtimes">
                        <div class="day"><h3>Sunday</h3><div class="time-slot">01:00PM</div><div class="time-slot">04:00PM</div></div>
                        <div class="day"><h3>Monday</h3><div class="time-slot">01:00PM</div></div>
                        <div class="day"><h3>Tuesday</h3><div class="time-slot">03:00PM</div></div>
                    </div>
                    <a href="${pageContext.request.contextPath}/book" class="book-btn">Book</a>
                </div>
            </div>
        </div>

    </div> <!-- End of second row -->

</div> <!-- End of schedule-container -->

<%@ include file="footer.jsp" %>
