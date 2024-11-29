/*
Name: Mustafa Amer
UCID: 30221271
Professor: Leonard Manzara
CPSC 355 Fall 2024
*/

//Defining Macros
define(month, w24)
define(day, w25)
define(year, x26)
define(i_r, w19)
define(base_month_r, x22)
define(base_prefix_r, x23)
define(argc_r, w20)
define(argv_r, x21)

	.text

//Declaring printing statements
fmt:	.string "%s %d%s, %d\n"
fmt2:	.string "usage: a5b mm dd yyyy\n"


//Declare month names Strings
jan:	.string "January"
feb:	.string "February"
mar:	.string "March"
apr:	.string "April"
may:	.string "May"
jun:	.string "June"
jul:	.string "July"
aug:	.string "August"
sep:	.string "September"
oct:	.string "October"
nov:	.string "November"
dec:	.string "December"
st:	.string "st"
nd: 	.string "nd"
rd: 	.string "rd"
th: 	.string "th"

	.data
//External pointer arrays
month_m:
	.dword jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec
prefix_m:
	.dword st, nd, rd, th, th, th, th, th, th, th, th, th, th, th, th, th, th, th, th, th, st, nd, rd, th, th, th, th, th, th, th, st

	.text
    	.balign 4
    	.global main
main:

	stp 	x29, x30, [sp, -16]!				//Aloocating memory in stack
    	mov 	x29, sp						//Moving sp into fp

    	mov 	argc_r, w0                               	//Takes number of arguements and stores in argc_r
    	mov 	argv_r, x1                                    	//Takes the arguments and stores in argv_r


    	cmp 	argc_r, 4                                     	//argc_r != 4
    	b.ne 	error                                        	//branches to error

    	mov 	i_r, 1                                        	//i_r = 1

    	ldr 	x0, [argv_r, i_r, SXTW 3]                     	//Loads the second argument from argv_r to the register
    	bl 	atoi                                           	//Calls the atoi function
    	mov 	month, w0                                     	//Moves the argument to month

    	cmp 	month, 0                                      	//month < 1
    	b.lt 	error                                        	//branches to error

    	cmp 	month, 12                                    	//month > 12
    	b.gt 	error                                        	//branches to error

    	mov 	i_r, 2                                        	//i_r = 2
    	ldr 	x0, [argv_r, i_r, SXTW 3]                     	//Loads the third argument from argv_r to a register
    	bl 	atoi                                           	//Calls the atoi function
    	mov 	day, w0                                       	//Moves the argument to day

    	cmp 	day, 0                                        	//day < 0
    	b.lt 	error                                        	//branches to error

    	cmp 	day, 31                                       	//day > 31
    	b.gt 	error                                        	//branches to error

    	mov 	i_r, 3                                        	//i_r = 3
    	ldr 	x0, [argv_r, i_r, SXTW 3]                     	//Loads the fourth argument from argv_r to a register
    	bl 	atoi                                           	//Calls the atoi function
    	mov 	year, x0                                      	//Moves the argument to year

    	cmp 	year, 0                                       	//year < 0
    	b.lt 	error                                        	//branches to error

    	adrp 	base_month_r, month_m                        	//Sets 1st arg (external array month)
    	add 	base_month_r, base_month_r, :lo12:month_m
    	sub 	month, month, 1                               	//month -= 1

    	adrp 	base_prefix_r, prefix_m                      	//Sets 1st arg (external array prefix)
    	add 	base_prefix_r, base_prefix_r, :lo12:prefix_m  	//Sets 1st arg (low order bits)

    	adrp 	x0, fmt                                      	//Sets 1st arg
    	add 	x0, x0, :lo12:fmt                             	//Sets 1st arg (low order bits)

    	ldr 	x1, [base_month_r, month, SXTW 3]             	//Sets 2nd arg
    	mov 	w2, day                                       	//Sets 3rd arg
    	sub 	day, day, 1                                   	//day -= 1

    	ldr 	x3, [base_prefix_r, day, SXTW 3]              	//Sets 4rd arg

    	mov 	x4, year                                      	//Sets 5th arg
    	bl 	printf                                         	//Calls the print function

    	b 	exit                                            //Branches to done

error:
    	adrp 	x0, fmt2                                     	//Sets 1st arg
    	add 	x0, x0, :lo12:fmt2
    	bl 	printf                                         	//Calls the print function

exit:
    	ldp 	x29, x30, [sp], 16				//Deallocates memory from stack
    	ret							//Returns to the OS
