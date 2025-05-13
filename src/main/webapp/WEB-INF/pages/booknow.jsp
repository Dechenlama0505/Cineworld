<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/book.css">

<div class="main-container">
    <div class="booking-form-section">
        <div class="booking-form">
            <form id="bookForm" action="${pageContext.request.contextPath}/booknow" method="post">
                <div class="form-row">
                    <div class="form-group">
                        <label for="bookingId">ID</label>
                        <input type="text" id="bookingId" name="bookingId" required>
                    </div>
                    <div class="form-group">
                        <label for="bookingName">Name</label>
                        <input type="text" id="bookingName" name="bookingName" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="bookingDate">Date</label>
                        <input type="date" id="bookingDate" name="bookingDate" required>
                    </div>
                    <div class="form-group">
                        <label for="bookingSeat">Seat</label>
                        <input type="text" id="bookingSeat" name="bookingSeat" readonly required>
                    </div>
                </div>

                <div class="form-row dropdowns">
                    <div class="form-group">
                        <select id="bookingMovie" name="bookingMovie" required>
                            <option value="" disabled selected>Movie</option>
                            <option value="Dune">Dune</option>
                            <option value="Inception">Inception</option>
                            <option value="Eternals">Eternals</option>
                            <option value="Crazy Rich Asians">Crazy Rich Asians</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select id="bookingTime" name="bookingTime" required>
                            <option value="" disabled selected>Time</option>
                            <option value="01:00PM">01:00PM</option>
                            <option value="03:00PM">03:00PM</option>
                            <option value="04:00PM">04:00PM</option>
                        </select>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="action-btn">ADD</button>
                    <button type="reset" class="action-btn">CLEAR</button>
                </div>
            </form>
        </div>

        <div class="seat-selection">
            <div class="screen">SCREEN</div>

            <div class="seats-container">
                <% 
                    // Logic for generating the seat rows and columns
                    String[] rows = {"A", "B", "C", "D"};
                    for (String row : rows) {
                        out.print("<div class='seat-row'>");
                        for (int col = 1; col <= 5; col++) {
                            String seat = row + col;
                            out.print("<div class='seat' data-seat='" + seat + "'>" + seat + "</div>");
                        }
                        out.print("</div>");
                    }
                %>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const seats = document.querySelectorAll('.seat');
        const seatInput = document.getElementById('bookingSeat');

        seats.forEach(seat => {
            seat.addEventListener('click', function () {
                document.querySelectorAll('.seat.selected').forEach(s => s.classList.remove('selected'));
                this.classList.add('selected');
                seatInput.value = this.dataset.seat;
            });
        });
    });
</script>

<%@ include file="footer.jsp" %>
