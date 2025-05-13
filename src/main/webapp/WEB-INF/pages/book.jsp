<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminheader.jsp" %>

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
                        <th>Movie</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Seat</th>
                    </tr>
                </thead>
                <tbody id="bookingsTableBody">
                    <!-- Bookings will be populated here -->
                </tbody>
            </table>
        </div>
    </div>
    
    <div class="booking-form-section">
        <div class="booking-form">
            <div class="form-row">
                <div class="form-group">
                    <label for="bookingId">ID</label>
                    <input type="text" id="bookingId" name="bookingId" readonly>
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
                <button id="addBtn" class="action-btn">ADD</button>
                <button id="deleteBtn" class="action-btn">DELETE</button>
                <button id="updateBtn" class="action-btn">UPDATE</button>
                <button id="clearBtn" class="action-btn">CLEAR</button>
            </div>
        </div>
        
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
    // Booking functionality
    document.addEventListener('DOMContentLoaded', function() {
        let bookings = [];
        let selectedSeat = null;
        let currentBookingId = 1;
        
        // Seat selection
        const seats = document.querySelectorAll('.seat');
        seats.forEach(seat => {
            seat.addEventListener('click', function() {
                // Remove selected class from all seats
                seats.forEach(s => s.classList.remove('selected'));
                
                // Add selected class to clicked seat
                this.classList.add('selected');
                
                // Update seat input
                selectedSeat = this.getAttribute('data-seat');
                document.getElementById('bookingSeat').value = selectedSeat;
            });
        });
        
        // Add booking
        document.getElementById('addBtn').addEventListener('click', function() {
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
            
            // Check if seat is already booked for this movie, date and time
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
        });
        
        // Delete booking
        document.getElementById('deleteBtn').addEventListener('click', function() {
            const id = document.getElementById('bookingId').value;
            
            if (!id) {
                alert('Please select a booking to delete');
                return;
            }
            
            bookings = bookings.filter(booking => booking.id !== parseInt(id));
            updateBookingsTable();
            clearForm();
        });
        
        // Update booking
        document.getElementById('updateBtn').addEventListener('click', function() {
            const id = document.getElementById('bookingId').value;
            
            if (!id) {
                alert('Please select a booking to update');
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
            
            const bookingIndex = bookings.findIndex(booking => booking.id === parseInt(id));
            
            if (bookingIndex !== -1) {
                bookings[bookingIndex] = {
                    id: parseInt(id),
                    name: name,
                    movie: movie,
                    date: date,
                    time: time,
                    seat: seat
                };
                
                updateBookingsTable();
                clearForm();
            }
        });
        
        // Clear form
        document.getElementById('clearBtn').addEventListener('click', function() {
            clearForm();
        });
        
        // Search functionality
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
        
//problem and solution: when the booking was not visible on my table 

        // Update bookings table
       /*  function updateBookingsTable(bookingsToShow = bookings) {
            const tableBody = document.getElementById('bookingsTableBody');
            tableBody.innerHTML = '';
            
            bookingsToShow.forEach(booking => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${booking.id}</td>
                    <td>${booking.name}</td>
                    <td>${booking.movie}</td>
                    <td>${booking.date}</td>
                    <td>${booking.time}</td>
                    <td>${booking.seat}</td>
                `;
                
                row.addEventListener('click', function() {
                    document.getElementById('bookingId').value = booking.id;
                    document.getElementById('bookingName').value = booking.name;
                    
                    // Set select values
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
                    
                    // Highlight the selected seat
                    seats.forEach(seat => {
                        if (seat.getAttribute('data-seat') === booking.seat) {
                            seats.forEach(s => s.classList.remove('selected'));
                            seat.classList.add('selected');
                        }
                    });
                });
                
                tableBody.appendChild(row);
            });
        } */
        
        function updateBookingsTable(bookingsToShow = bookings) {
            const tableBody = document.getElementById('bookingsTableBody');
            tableBody.innerHTML = '';  // Clear the existing table rows

            bookingsToShow.forEach(booking => {
                const row = document.createElement('tr');  // Create a new row
               
                // Set text inside each <td> for the booking details
                const idCell = document.createElement('td');
                idCell.textContent = booking.id;  // Set the booking ID

                const nameCell = document.createElement('td');
                nameCell.textContent = booking.name;  // Set the booking name

                const movieCell = document.createElement('td');
                movieCell.textContent = booking.movie;  // Set the movie name

                const dateCell = document.createElement('td');
                dateCell.textContent = booking.date;  // Set the booking date

                const timeCell = document.createElement('td');
                timeCell.textContent = booking.time;  // Set the booking time

                const seatCell = document.createElement('td');
                seatCell.textContent = booking.seat;  // Set the seat number

                // Append the cells to the row
                row.appendChild(idCell);
                row.appendChild(nameCell);
                row.appendChild(movieCell);
                row.appendChild(dateCell);
                row.appendChild(timeCell);
                row.appendChild(seatCell);

                // Add a click event to each row
                row.addEventListener('click', function() {
                    document.getElementById('bookingId').value = booking.id;
                    document.getElementById('bookingName').value = booking.name;

                    // Set select values for movie and time
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

                    // Highlight the selected seat
                    seats.forEach(seat => {
                        if (seat.getAttribute('data-seat') === booking.seat) {
                            seats.forEach(s => s.classList.remove('selected'));
                            seat.classList.add('selected');
                        }
                    });
                });

                // Append the row to the table body
                tableBody.appendChild(row);
            });
        }
        
        // Clear form fields
        function clearForm() {
            document.getElementById('bookingId').value = '';
            document.getElementById('bookingName').value = '';
            document.getElementById('bookingMovie').selectedIndex = 0;
            document.getElementById('bookingDate').value = '';
            document.getElementById('bookingTime').selectedIndex = 0;
            document.getElementById('bookingSeat').value = '';
            
            // Clear seat selection
            seats.forEach(seat => seat.classList.remove('selected'));
            selectedSeat = null;
        }
    });
</script>

<%@ include file="footer.jsp" %>