/*
Name: Mustafa Amer
UCID: 30221271
CPSC 355, Fall 2024, Assignment 3
*/

fmt1:	.string "Unsorted Array: \n"
fmt2:	.string "Sorted Array: \n"
fmt3:	.string "v [ %d ]: %d\n"

//Assembler Equates
SIZE = 80						//Size of the array is 80
v_size = 4 * SIZE					//Allocating the size of array times 4 per section in memory
i_size = 4						//Size of counter i = 4
j_size = 4						//Size of counter j = 4
temp_size = 4						//Size of min = 4
min_size = 4						//Size of temp = 4

alloc = -(16 + 16 + v_size) & -16			//Amount of memory that needs to be allocated in stack
dealloc = -alloc					//Amount of memory that needs to be deallocated from stack at the end of the program

//Offsets for Assembler Equates
v_s = 16
i_s = 16 + v_size
j_s = i_s + i_size
temp_s = j_s + j_size
min_s = temp_s + temp_size

//Register Equates
fp .req x29						//New reference for frame pointer x29
lr .req x30						//New reference for load register x30


//Define Macros










main:
	.balign 4
	.global main
	stp	fp, lr, [sp, alloc]!
	mov	fp, sp

	mov	w20, 0
	str	w20, [fp, i_s]
	b	test1

initialize:
	bl	rand					//Calls the rand() function
	and	w21, w0, 0x1FF				//Mods the result with 512

	add	x19, fp, v_s			//Calculating the base address of the array
	ldr	w20, [fp, i_s]				//Loading the current index
	str	w21, [x19, w20, SXTW 2]		//Stores a random integer into array[i], uses LSL with SXTW and makes the offset

	//using print function to show initialized array
	adrp	x0, fmt3
	add	x0, x0, :lo12:fmt3
	ldr	w1, [fp, i_s]
	add	x19, fp, v_s
	ldr	w2, [x19, w20, SXTW 2]
	bl	printf

	//Incrementing i and storing it
	ldr	w20, [fp, i_s]				//Gets the current index in the array
	add	w20, w20, 1				//Increments the index by 1
	str	w20, [fp, i_s]				//Updates the frame pointer

test1:
	cmp	w20, SIZE
	b.lt	initialize

	adrp	x0, fmt1				//Prints out the unsorted array line after the print to show it
	add	x0, x0, :lo12:fmt1
	bl	printf

	mov	w20, 0
	str	w20, [fp, i_s]

	b	test2

loop2:
	ldr	w20, [fp, i_s]
	str	w20, [fp, min_s]

	ldr	w20, [fp, i_s]
	add	w21, w20, 1
	str	w21, [fp, j_s]

	b	test3

loop3:
	ldr	w21, [fp, j_s]
	ldr	w25, [x19, w21, SXTW 2]
	ldr	w23, [fp, min_s]
	ldr	w26, [x19, w23, SXTW 2]

	cmp	w25, w26
	b.lt	skip

	ldr	w21, [fp, j_s]
	mov	w23, w21
	str	w23, [fp, min_s]

skip:
	ldr	w21, [fp, j_s]
	add	w21, w21, 1
	str	w21, [fp, j_s]

test3:
	ldr	w21, [fp, j_s]
	cmp	w21, SIZE
	b.lt	loop3

	ldr	w23, [fp, min_s]
	ldr	w26, [x19, w23, SXTW 2]
	mov	w22, w26
	str	w22, [fp, temp_s]

	ldr	w20, [fp, i_s]
	ldr	w24, [x19, w20, SXTW 2]
	str	w24, [x19, w23, SXTW 2]

	str	w22, [x19, w20, SXTW 2]

	adrp	x0, fmt3
	add	x0, x0, :lo12:fmt3
	ldr	w1, [fp, i_s]
	ldr	w2, [x19, w1, SXTW 2]
	bl	printf

	add	w20, w20, 1
	str	w20, [fp, i_s]

test2:
	cmp	w20, SIZE
	b.lt	loop2

	adrp	x0, fmt2
	add	x0, x0, :lo12:fmt2
	bl	printf

	mov	w0, 0
exit:
	ldp	fp, lr, [sp], dealloc
	ret
