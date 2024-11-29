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
define(v_base_r, x19)
define(i_r, w20)
define(j_r, w21)
define(temp_r, w22)
define(min_r, w23)
define(icomp_r, w24)
define(jcomp_r, w25)
define(mincomp_r, w26)
define(temp2_r, w27)

main:
	.balign 4
	.global main
	stp	fp, lr, [sp, alloc]!
	mov	fp, sp

	mov	i_r, 0
	str	i_r, [fp, i_s]
	b	test1

initialize:
	bl	rand					//Calls the rand() function
	and	j_r, w0, 0x1FF				//Mods the result with 512

	add	v_base_r, fp, v_s			//Calculating the base address of the array
	ldr	i_r, [fp, i_s]				//Loading the current index
	str	j_r, [v_base_r, i_r, SXTW 2]		//Stores a random integer into array[i], uses LSL with SXTW and makes the offset

	//using print function to show initialized array
	adrp	x0, fmt3
	add	x0, x0, :lo12:fmt3
	ldr	w1, [fp, i_s]
	add	v_base_r, fp, v_s
	ldr	w2, [v_base_r, i_r, SXTW 2]
	bl	printf

	//Incrementing i and storing it
	ldr	i_r, [fp, i_s]				//Gets the current index in the array
	add	i_r, i_r, 1				//Increments the index by 1
	str	i_r, [fp, i_s]				//Updates the frame pointer

test1:
	cmp	i_r, SIZE
	b.lt	initialize

	adrp	x0, fmt1				//Prints out the unsorted array line after the print to show it
	add	x0, x0, :lo12:fmt1
	bl	printf

	mov	i_r, 0
	str	i_r, [fp, i_s]

	b	test2

loop2:
	ldr	i_r, [fp, i_s]				//Retrieving the current value of i from the stack
	str	i_r, [fp, min_s]			//Storing the value of min_r in the stack

	ldr	i_r, [fp, i_s]				//Loads the value of i_r from stack
	add	j_r, i_r, 1				//Increments index by 1
	str	j_r, [fp, j_s]				//Stores the value of j_r in stack

	b	test3					//Branches to test 3

loop3:
	ldr	j_r, [fp, j_s]				//Loads the value of j from stack
	ldr	jcomp_r, [v_base_r, j_r, SXTW 2]	//loads the value to compare from the array v[j]
	ldr	min_r, [fp, min_s]			//Retrives minimum value from stack
	ldr	mincomp_r, [v_base_r, min_r, SXTW 2]

	cmp	jcomp_r, mincomp_r			//Compares the array value to the minimum
	b.lt	skip					//Goes to the next one in the case that j is less than the old minimum value

	//Replacing the minimum register with the value of j
	ldr	j_r, [fp, j_s]
	mov	min_r, j_r
	str	min_r, [fp, min_s]

skip:
	//Incrementing j by 1
	ldr	j_r, [fp, j_s]
	add	j_r, j_r, 1
	str	j_r, [fp, j_s]

test3:
	ldr	j_r, [fp, j_s]				//Loads j index from stack
	cmp	j_r, SIZE				//Compares the j index to the size of the array
	b.lt	loop3					//If it is less than the size of the array then it continues on looping

	ldr	min_r, [fp, min_s]			//Loads the value of index min_r from stack
	ldr	mincomp_r, [v_base_r, min_r, SXTW 2]	//Loads the value of v[min]
	mov	temp_r, mincomp_r			//Stores it in a temp register
	str	temp_r, [fp, temp_s]			//Stores that value in the stack

	ldr	i_r, [fp, i_s]				//Loads the current index of i from the stack
	ldr	icomp_r, [v_base_r, i_r, SXTW 2]	//Loads the value of v[i] from the array in stack
	str	icomp_r, [v_base_r, min_r, SXTW 2]	//Stores it in the array and stack

	str	temp_r, [v_base_r, i_r, SXTW 2]		//Stores the temp value in the stack at index i

	//Prints the sorted array in a loop
	adrp	x0, fmt3
	add	x0, x0, :lo12:fmt3
	ldr	w1, [fp, i_s]
	ldr	w2, [v_base_r, w1, SXTW 2]
	bl	printf

	//Increments the i index by 1
	add	i_r, i_r, 1
	str	i_r, [fp, i_s]

test2:
	cmp	i_r, SIZE				//Compares the i index to the size of the array
	b.lt	loop2					//Goes back to the loop until the entire array is gone through and printed

	//Prints out a label at the end for the sorted array
	adrp	x0, fmt2
	add	x0, x0, :lo12:fmt2
	bl	printf

	mov	w0, 0
exit:
	ldp	fp, lr, [sp], dealloc			//Deallocates the memory from the stack
	ret						//Returns to the OS
