# Pandemic
This program tracks Covid19 data from 3-1-2020 through 3-17-23. A user can add additional dates, modify dates, or delete dates to see how the program functions.
I created a MySQL database (covid19db) to store, retrieve, and manipulate the data. It uses ResultSets and PreparedStatements to execute queries. You must be
running MySQL 8.3 in order for program to run. The main lesson learned here was using @increment in PreparedStatements to update data for entire column in table.
