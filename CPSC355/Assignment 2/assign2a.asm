/*
Name: Mustafa Amer
UCID: 30221271
Description: Assignment 2 CPSC 355
TA: Ashraful Alam
Professor: Manzara
*/

define(x_r, w19)
define(y_r, w20)
define(t1_r, w21)
define(t2_r, w22)
define(t3_r, w23)
define(t4_r, w24)

fmt:	.string "Original hexa: 0x%08x reversed hexa: 0x%08x\nOriginal binary: %b reversed binary: %b\n"

	.balign 4
	.global main
main:	stp	x29, x30, [sp, -16]					//Main to initialize the stack pointer
	mov	x29, sp

	mov	x_r, 0x07FC07FC						//Stores the value of x in x_r

step1:	and	t1_r, x_r, 0x55555555					//x AND 0x55555555 is being stored into t1_r
	lsl	t1_r, t1_r, 1						//t1_r is being multiplied by 2
	lsr	t2_r, x_r, 1						//logical shift right on x and stored in t2_r
	and	t2_r, t2_r, 0x55555555					//ands the previous lines answer with 0x55555555
	orr	y_r, t1_r, t2_r						//Uses the OR operand on t1 and t2 and stores it in y

step2:	and	t1_r, y_r, 0x33333333					//Performs functions in step 2
	lsl	t1_r, t1_r, 2
	lsr	t2_r, y_r, 2
	and	t2_r, t2_r, 0x33333333
	orr	y_r, t1_r, t2_r						//Uses the OR operand on t1 and t2 and stores it in y

step3:	and	t1_r, y_r, 0x0F0F0F0F
	lsl	t1_r, t1_r, 4
	lsr	t2_r, y_r, 4
	and	t2_r, t2_r, 0x0F0F0F0F
	orr	y_r, t1_r, t2_r						//Uses the OR operand on t1 and t2 and stores it in y

step4:	lsl	t1_r, y_r, 24
	and	t2_r, y_r, 0xFF00
	lsl	t2_r, t2_r, 8
	lsr	t3_r, y_r, 8
	and	t3_r, t3_r, 0xFF00
	lsr	t4_r, y_r, 24
	orr	y_r, t1_r, t2_r						//uses orr multiple times to do y_r | t1 | t2 |t3 | t4
	orr	y_r, y_r, t3_r
	orr	y_r, y_r, t4_r

print:	adrp	x0, fmt							//Prints the output of the program
	add	x0, x0, :lo12:fmt
	mov	w1, x_r
	mov	w2, y_r
	mov	w3, x_r
	mov	w4, y_r
	bl	printf

exit:	ldp	x29, x30, [sp, 16]					//Unloads the stack pointer and returns the program
	ret
