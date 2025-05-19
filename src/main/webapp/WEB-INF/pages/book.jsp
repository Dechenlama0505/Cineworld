<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminheader.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/book.css">

<div class="main-container">
    <div class="bookings-section">
        <h2>BOOKINGS</h2>
        
        <div class="search-bar">
            <input type="text" placeholder="Search..." id="searchInput">
            <i class="fas fa-search"></i>
        </div>
        
        <div class="bookings-table">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Seat</th>
                        <th>Movie</th>
                        <th>Time</th>
                    </tr>
                </thead>
                <tbody id="bookingsTableBody">
                    <c:forEach var="user" items="${bookings}">
                        <tr>
                            <td><c:out value="${user.bookingId}" /></td>
                            <td><c:out value="${user.bookingName}" /></td>
                            <td><c:out value="${user.bookingDate}" /></td>
                            <td><c:out value="${user.seatNumber}" /></td>
                            <td><c:out value="${user.movie}" /></td>
                            <td><c:out value="${user.time}" /></td>		    
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <div class="booking-form-section">
        <form id="bookingForm" action="${pageContext.request.contextPath}/admin/bookcontrol" method="post">
            <div class="booking-form">
                <div class="form-row">
                    <div class="form-group">
                        <label for="bookingId">ID</label>
                        <input type="text" id="bookingId" name="bookingId">
                    </div>
                    <div class="form-group">
                        <label for="bookingName">Name</label>
                        <input type="text" id="bookingName" name="bookingName">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="bookingDate">Date</label>
                        <input type="date" id="bookingDate" name="bookingDate">
                    </div>
                    <div class="form-group">
                        <label for="bookingSeat">Seat</label>
                        <input type="text" id="bookingSeat" name="bookingSeat" readonly>
                    </div>
                </div>
                
                <div class="form-row dropdowns">
                    <div class="form-group">
                        <select id="bookingTime" name="bookingTime">
                            <option value="" disabled selected>Time</option>
                            <option value="01:00PM">01:00PM</option>
                            <option value="03:00PM">03:00PM</option>
                            <option value="04:00PM">04:00PM</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select id="bookingMovie" name="bookingMovie">
                            <option value="" disabled selected>Movie</option>
                            <option value="Dune">Dune</option>
                            <option value="Inceptions">Inceptions</option>
                            <option value="Eternals">Eternals</option>
                            <option value="Crazy Rich Asians">Crazy Rich Asians</option>
                        </select>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" id="addBtn" class="action-btn" name="action" value="add">ADD</button>
                    <button type="submit" id="deleteBtn" class="action-btn" name="action" value="delete">DELETE</button>
                    <button type="submit" id="updateBtn" class="action-btn" name="action" value="update">UPDATE</button>
                    <button type="button" id="clearBtn" class="action-btn">CLEAR</button> 
                </div>
            </div>
        </form>

        <div class="seat-selection">
            <div class="screen">SCREEN</div>
            <div class="seats-container">
                <div class="seat-row">
                    <div class="seat" data-seat="A1">A1</div>
                    <div class="seat" data-seat="A2">A2</div>
                    <div class="seat" data-seat="A3">A3</div>
                    <div class="seat" data-seat="A4">A4</div>
                    <div class="seat" data-seat="A5">A5</div>
                </div>
                <div class="seat-row">
                    <div class="seat" data-seat="B1">B1</div>
                    <div class="seat" data-seat="B2">B2</div>
                    <div class="seat" data-seat="B3">B3</div>
                    <div class="seat" data-seat="B4">B4</div>
                    <div class="seat" data-seat="B5">B5</div>
                </div>
                <div class="seat-row">
                    <div class="seat" data-seat="C1">C1</div>
                    <div class="seat" data-seat="C2">C2</div>
                    <div class="seat" data-seat="C3">C3</div>
                    <div class="seat" data-seat="C4">C4</div>
                    <div class="seat" data-seat="C5">C5</div>
                </div>
                <div class="seat-row">
                    <div class="seat" data-seat="D1">D1</div>
                    <div class="seat" data-seat="D2">D2</div>
                    <div class="seat" data-seat="D3">D3</div>
                    <div class="seat" data-seat="D4">D4</div>
                    <div class="seat" data-seat="D5">D5</div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    let selectedSeat = null;

    const seats = document.querySelectorAll('.seat');
    seats.forEach(seat => {
        seat.addEventListener('click', function() {
            seats.forEach(s => s.classList.remove('selected'));
            this.classList.add('selected');
            selectedSeat = this.getAttribute('data-seat');
            document.getElementById('bookingSeat').value = selectedSeat;
        });
    });

    document.getElementById('clearBtn').addEventListener('click', function() {
        document.getElementById('bookingId').value = '';
        document.getElementById('bookingName').value = '';
        document.getElementById('bookingDate').value = '';
        document.getElementById('bookingSeat').value = '';
        document.getElementById('bookingMovie').selectedIndex = 0;
        document.getElementById('bookingTime').selectedIndex = 0;
        seats.forEach(seat => seat.classList.remove('selected'));
        selectedSeat = null;
        alert('Booking Cleared');
    });

    const form = document.getElementById('bookingForm');
    form.addEventListener('submit', function(event) {
        const action = event.submitter.value;
        const id = document.getElementById('bookingId').value.trim();
        const name = document.getElementById('bookingName').value.trim();
        const date = document.getElementById('bookingDate').value.trim();
        const seat = document.getElementById('bookingSeat').value.trim();
        const movie = document.getElementById('bookingMovie').value;
        const time = document.getElementById('bookingTime').value;

        // Validate inputs
        if (action !== "delete" && (!name || !date || !seat || !movie || !time)) {
            alert('Please fill in all fields and select a seat');
            event.preventDefault();
            return;
        }

        if ((action === "update" || action === "delete") && !id) {
            alert(`Please enter Booking ID to ${action}`);
            event.preventDefault();
            return;
        }

        // Dialog box feedback before submit (assumes backend handles operation)
        switch(action) {
            case "add":
                alert("Booking Added");
                break;
            case "delete":
                alert("Booking Deleted");
                break;
            case "update":
                alert("Booking Updated");
                break;
        }
    });
});
</script>


<!--  <script>
document.addEventListener('DOMContentLoaded', function() {
    let bookings = [];
    let selectedSeat = null;
    let currentBookingId = 1;

    const seats = document.querySelectorAll('.seat');
    seats.forEach(seat => {
        seat.addEventListener('click', function() {
            seats.forEach(s => s.classList.remove('selected'));
            this.classList.add('selected');
            selectedSeat = this.getAttribute('data-seat');
            document.getElementById('bookingSeat').value = selectedSeat;
        });
    });

    document.getElementById('addBtn').addEventListener('click', function(event) {
        event.preventDefault();
        const name = document.getElementById('bookingName').value;
        const movie = document.getElementById('bookingMovie').value;
        const date = document.getElementById('bookingDate').value;
        const time = document.getElementById('bookingTime').value;
        const seat = document.getElementById('bookingSeat').value;

        if (!name || !movie || !date || !time || !seat) {
            alert('Please fill in all fields and select a seat');
            return;
        }

        const seatBooked = bookings.some(booking => 
            booking.movie === movie && 
            booking.date === date && 
            booking.time === time && 
            booking.seat === seat
        );

        if (seatBooked) {
            alert('This seat is already booked for this showing');
            return;
        }

        const booking = {
            id: currentBookingId++,
            name: name,
            movie: movie,
            date: date,
            time: time,
            seat: seat
        };

        bookings.push(booking);
        updateBookingsTable();
        clearForm();
        alert('Booking Added');
    });

    document.getElementById('deleteBtn').addEventListener('click', function() {
        const id = parseInt(document.getElementById('bookingId').value);
        if (!id) {
            alert('Please select a booking to delete');
            return;
        }

        const exists = bookings.some(booking => booking.id === id);
        if (!exists) {
            alert('Booking ID does not exist to delete');
            return;
        }

        bookings = bookings.filter(booking => booking.id !== id);
        updateBookingsTable();
        clearForm();
        alert('Booking Deleted');
    });

    document.getElementById('updateBtn').addEventListener('click', function() {
        const id = parseInt(document.getElementById('bookingId').value);
        if (!id) {
            alert('Please select a booking to update');
            return;
        }

        const bookingIndex = bookings.findIndex(booking => booking.id === id);
        if (bookingIndex === -1) {
            alert('Booking ID does not exist to update');
            return;
        }

        const name = document.getElementById('bookingName').value;
        const movie = document.getElementById('bookingMovie').value;
        const date = document.getElementById('bookingDate').value;
        const time = document.getElementById('bookingTime').value;
        const seat = document.getElementById('bookingSeat').value;

        if (!name || !movie || !date || !time || !seat) {
            alert('Please fill in all fields and select a seat');
            return;
        }

        bookings[bookingIndex] = {
            id: id,
            name: name,
            movie: movie,
            date: date,
            time: time,
            seat: seat
        };
        updateBookingsTable();
        clearForm();
        alert('Booking Updated');
    });

    document.getElementById('clearBtn').addEventListener('click', function() {
        clearForm();
        alert('Booking Cleared');
    });

    document.getElementById('searchInput').addEventListener('keyup', function() {
        const searchValue = this.value.toLowerCase();
        const filteredBookings = bookings.filter(booking => 
            booking.name.toLowerCase().includes(searchValue) ||
            booking.movie.toLowerCase().includes(searchValue) ||
            booking.date.includes(searchValue) ||
            booking.time.toLowerCase().includes(searchValue) ||
            booking.seat.toLowerCase().includes(searchValue)
        );
        updateBookingsTable(filteredBookings);
    });

    function updateBookingsTable(bookingsToShow = bookings) {
        const tableBody = document.getElementById('bookingsTableBody');
        tableBody.innerHTML = '';

        bookingsToShow.forEach(booking => {
            const row = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = booking.id;

            const nameCell = document.createElement('td');
            nameCell.textContent = booking.name;

            const movieCell = document.createElement('td');
            movieCell.textContent = booking.movie;

            const dateCell = document.createElement('td');
            dateCell.textContent = booking.date;

            const timeCell = document.createElement('td');
            timeCell.textContent = booking.time;

            const seatCell = document.createElement('td');
            seatCell.textContent = booking.seat;

            row.appendChild(idCell);
            row.appendChild(nameCell);
            row.appendChild(movieCell);
            row.appendChild(dateCell);
            row.appendChild(timeCell);
            row.appendChild(seatCell);

            row.addEventListener('click', function() {
                document.getElementById('bookingId').value = booking.id;
                document.getElementById('bookingName').value = booking.name;

                const movieSelect = document.getElementById('bookingMovie');
                const timeSelect = document.getElementById('bookingTime');

                for (let i = 0; i < movieSelect.options.length; i++) {
                    if (movieSelect.options[i].value === booking.movie) {
                        movieSelect.selectedIndex = i;
                        break;
                    }
                }

                for (let i = 0; i < timeSelect.options.length; i++) {
                    if (timeSelect.options[i].value === booking.time) {
                        timeSelect.selectedIndex = i;
                        break;
                    }
                }

                document.getElementById('bookingDate').value = booking.date;
                document.getElementById('bookingSeat').value = booking.seat;

                seats.forEach(seat => {
                    seat.classList.remove('selected');
                    if (seat.getAttribute('data-seat') === booking.seat) {
                        seat.classList.add('selected');
                    }
                });
            });

            tableBody.appendChild(row);
        });
    }

    function clearForm() {
        document.getElementById('bookingId').value = '';
        document.getElementById('bookingName').value = '';
        document.getElementById('bookingMovie').selectedIndex = 0;
        document.getElementById('bookingDate').value = '';
        document.getElementById('bookingTime').selectedIndex = 0;
        document.getElementById('bookingSeat').value = '';
        seats.forEach(seat => seat.classList.remove('selected'));
        selectedSeat = null;
    }
});
</script> -->

<%@ include file="footer.jsp" %>
