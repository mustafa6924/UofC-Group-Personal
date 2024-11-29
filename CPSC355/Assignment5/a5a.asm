/*
Assignment 5a
CPSC 355 Fall 2024
Name: Mustafa Amer
UCID 30221271
Professor: Leonard Manzara
*/


//Assembler Equates
MAXOP = 20
NUMBER = '0'
TOOBIG = '9'
MAXVAL = 100
BUFSIZE = 100

//Defining Macros
define(i_r, w19)
define(c_r, w20)
define(base_r, x21)
define(lim_r, w22)
define(sp_r, w23)
define(f_r, w24)

//Print statements used in the code
stackFullString:
	.string "error: stack full\n"
stackEmptyString:
	.string "error: stack empty\n"
ungetchString:
	.string "ungetch: too many characters\n"

//Global Variables
	.data							//Data Section start
	.global sp_m
sp_m:	.word 0

	.global bufp
bufp:	.word 0

	.bss							// start of the bss section
	.global val
val_m:	.skip MAXVAL * 4					//Allocates 400 bytes of memory to stack

	.global buf
buf:	.skip BUFSIZE * 4					//Allocates 400 bytes to stack


	.text
	.balign 4

	.global push						//Make push() global
//push() function
push:
	stp	x29, x30, [sp, -16]!				//Allocatig memory in stack
	mov	x29, sp						//Moving the sp into the frame pointer so they point at the same address

	mov	f_r, 0						//Move the parameter int f into f_r

	adrp 	x25, sp_m					//Place sp address in x25 register
	add 	x25, x25, :lo12:sp_m
	ldr 	sp_r, [x25]					//Load into sp_r

	cmp 	sp_r, MAXVAL					//Compare sp and MAXVAL
	b.ge 	push_else					//If sp >= MAXVAL jump to push_else, else continue down

	adrp 	x26, val_m					//Place val address in x26 register
	add 	x26, x26, :lo12:val_m
	ldr 	f_r, [x26, sp_r, SXTW 2]			//Load into f_r

	adrp	x26, val_m					//Place val address into first argument
	add	x26, x26, :lo12:val_m
	ldr	w0, [x26, sp_r, SXTW 2]				//Returns f

	add 	sp_r, sp_r, 1					//sp--: sp, sp, 1 (add 1 to sp)
	str 	sp_r, [x25]					//Replace new sp entry with old one

	b	push_exit

push_else:
	adrp 	x0, stackFullString				//Print error message: "error: stack full\n"
	add 	x0, x0, :lo12:stackFullString
	bl 	printf						//Print message
	bl 	clear						//Branch to clear function
	mov 	w0, 0						//Get function to return 0

push_exit:
	ldp	x29, x30, [sp], 16				//Deallocate memory from stack
	ret							//Returns to the OS

	.global pop						//Make pop() global
//pop() function
pop:
	stp	x29, x30, [sp, -16]!				//Allocating memory in stack
	mov	x29, sp						//Moving the sp into fp so they point at the same address

	adrp 	x9, sp_m					//Place sp address in x11 register
	add 	x9, x9, :lo12:sp_m				//Format lower 12 bits correctly
	ldr 	sp_r, [x9]					//Set w11 to the value of x11

	cmp 	sp_r, 0						//Compare sp and 0
	b.le 	pop_else					//If sp > 0 jump to pop_else, else continue down

	sub	sp_r, sp_r, 1					//Decrement sp_r by 1
	str	sp_r, [x9]					//Stores this into the stack

	adrp	x9, sp_m					//Sets up the first argument
	add	x9, x9, :lo12:sp_m
	ldr	sp_r, [x9]					//Loads sp_r from stack

	adrp	x9, val_m					//Sets up the first argument
	add	x9, x9, :lo12:val_m
	SXTW	x25, sp_r					//Sign extends sp_r
	ldr	w0, [x9, x25, LSL 2]				//returns val[--sp]

	b	pop_exit

pop_else:
	adrp 	x0, stackEmptyString				//Print error message: "error: stack empty\n"
	add 	x0, x0, :lo12:stackEmptyString			//Add string to x9 register
	bl 	printf						//Print message
	bl 	clear						//Branch to clear function
	mov 	w0, 0						//Get function to return 0

pop_exit:
	ldp	x29, x30, [sp], 16				//Deallocates memory from stack
	ret							//Returns to the OS

	.global clear						//Make clear() global
//clear()
clear:
	stp	x29, x30, [sp, -16]!				//Allocating memory to stack
	mov	x29, sp						//Moving the sp into fp so they point at the same address

	mov	sp_r, 0						//sp_r = 0
	adrp	x25, sp_m					//Sets up the first argument
	add	x25, x25, :lo12:sp_m
	str	sp_r, [x25]					//Stores sp_r into the stack

	ldp 	x29, x30, [sp], 16				//Deallocating memoryu from stack
	ret							//Returns to the OS

	.global getop						//Make getop() global
//getop()
getop:
	stp	x29, x30, [sp, -16]!				//Allocating memory to stack
	mov	x29, sp						//Moving sp into fp so they point at the same address

	mov	base_r, x0					//Store the first parameter char *s to base_r
	mov	lim_r, w1					//Store the second parameter int into w11

test1:                                                          //while loop begins
    	bl 	getch                                           //Calls the getch function
    	mov 	c_r, w0                                         //Stores the return value from getch to c_r

    	cmp 	c_r, ' '                                        //c == ' '
    	b.eq 	test1                                           //Branches to test1

    	cmp 	c_r, '\t'                                       //c == '\t'
    	b.eq 	test1                                           //Branches to test1

    	cmp 	c_r, '\n'                                       //c == '\n'
    	b.eq 	test1                                           //Branches to test1

if1:
	cmp	c_r, '0'					//c_r >= '0'
	b.lt	cont						//Branches to the continue branch

	cmp	c_r, '9'					//c_r <= '9'
	b.le	next1

cont:
	mov	w0, c_r						//Returns x11
	b	getop_exit					//Branches to exit

next1:
	str	c_r, [base_r]					//s[0] = c

loop1:
	mov	i_r, 0						//Storing i into i_r and setting it to 0
	b	for1						//Branching to for1

if2:
	cmp	i_r, lim_r					//Compares the value of i with lim
	b.ge	for1						//Branches to for1 if greater than equals to

	str	c_r, [base_r, i_r, SXTW 2]			//s[i] = c

for1:
	add	i_r, i_r, 1					//Increments i by 1
	bl	getchar						//Calls the getchar function
	mov	c_r, w0						//Stores the result in c_r

	cmp	c_r, '0'					//Compares c_r with '0'
	b.lt	if3						//Branches to if3 if less than

	cmp	c_r, '9'					//Compares c_r with '9'
	b.le	if2						//Branches to if2

if3:
	cmp	i_r, lim_r					//Compares i with lim
	b.ge	else3						//Branches to else 3 if greater than or equal to

	mov	w0, c_r						//Moves c_r into the result
	bl	ungetch						//Calls the ungetch() function

	str	wzr, [base_r, i_r, SXTW 2]			//s[i] = '\0'
	mov	x0, NUMBER					//returns NUMBER
	b	getop_exit					//branches to done3

else3:

while1:
	cmp	c_r, '\n'					//c == \n
	b.eq	next2						//Branches to next2 to if equal

	cmp	c_r, -1						//c == -1
	b.eq	next2						//Branches to next2 if equal

	bl	getchar						//Calls the getchar function
	mov	c_r, w0						//Puts this into c_r which is c
	b	while1						//Loops

next2:
	sub	lim_r, lim_r, 1					//Decrement lim by 1
	str	wzr, [base_r, lim_r, SXTW 2]			//s[lim-1] = '\0'

	mov	w0, TOOBIG					//Returns TOOBIG

getop_exit:
	ldp	x29, x30, [sp], 16				//Deallocates memory from stack
	ret							//Returns to the OS

	.global getch						//Make getch() global
//getch()
getch:
	stp	x29, x30, [sp, -16]!				//Allocating memory to stack
	mov	x29, sp						//Stores sp into fp so they point to the same address

	adrp	x11, bufp					//Sets up first argument
	add	x11, x11, :lo12:bufp
	ldr	w12, [x11]					//Loads bufp into register
if4:
	cmp	w12, 0						//bufp <=0
	b.le	else4						//branches to else4

	sub	w12, w12, 1					//Decrements bufp by 1
	str	w12, [x11]					//Stores this decremented bufp to stack

	adrp	x11, buf					//Sets first argument
	add	x11, x11, :lo12:buf
	SXTW	x12, w12					//Sign extend bufp
	ldrb	w0, [x11, x12]					//Returns buf[--bufp]
	b	getch_exit					//Branches to getch_exit


else4:
	bl	getchar						//Calls the getchar function

getch_exit:
	ldp	x29, x30, [sp], 16				//Deallocate memory from stack
	ret							//Returns to the OS

	.global ungetch						//Make ungetch() global
//ungetch()
ungetch:
	stp	x29, x30, [sp, -16]!				//Allocates memory to stack
	mov	x29, sp						//Moves sp into fp so they point at the same address

	mov	c_r, w0						//Moves parameter into c_r

	adrp	x11, bufp					//Sets first argument
	add	x11, x11, :lo12:bufp
	ldr	w12, [x11]					//Loads bufp from stakc into w12

	cmp	w12, BUFSIZE					//bufp <= BUFSIZE
	b.le	else5						//Branches to else5

	//Printing out ungetch message
	adrp	x0, ungetchString
	add	x0, x0, :lo12:ungetchString
	bl	printf

	b	ungetch_exit

else5:
	adrp	x13, buf					//Setting up first argument
	add	x13, x13, :lo12:buf
	str	w10, [x13, w12, SXTW 2]				//buf[buffp++] = c

	add	w12, w12, 1					//Incrementing bufp by 1
	str	w12, [x11]					//Stores incremented value onto stack

ungetch_exit:
	ldp	x29, x30, [sp], 16				//Deallocating memory from stack
	ret							//Returns to OS
