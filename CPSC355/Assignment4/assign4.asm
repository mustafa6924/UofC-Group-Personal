/*
Name: Muhammad Mustafa Amer
UCID: 30221271
Assignment 4 CPSC 355 Fall 2024
*/

fmt:	.string "Initial box values: \n"

fmt1:	.string "Box %s : origin = (%d, %d) width = %d height = %d area = %d\n"

fmt2:	.string	"\nChanged box values:\n"

s_first:
	.string	"first"

s_second:
	.string "second"

	.balign 4

	//Assembler Equates
	FALSE = 0
	TRUE = 1
	//struct point() declaration
	point_x = 0						//Offset
	point_y = 4						//Offset
	point_size = 8
	//struct dimension() declaration
	dim_width = 0						//Offset
	dim_height = 4						//Offset
	dim_size = 8
	//struct box() declaration
	box_origin = 0						//Offset
	box_size = 8						//Offset
	box_area = 16						//Offset
	box_total_size = 20

	//Calculate how much memory to allocate
	alloc = -(16 + box_total_size + box_total_size) & -16
	dealloc = -alloc

	//Offsets
	box_s = 16
	box2_s = 16 + box_total_size

	//Defining macros
	define(box1_r, x19)
	define(box2_r, x20)

	//Defining Macros
	define(box1_base_r, x22)
	define(box2_base_r, x23)

	//Defining macros
	define(box_base_r, x21)

	fp .req x29
	lr .req x30


	.global main
main:
	stp	fp, lr, [sp, alloc]!				//Allocate memory to stack
	mov	fp, sp						//Move sp into fp so that they both point to the same location

	add	box1_r, fp, box_s				//Calculates the base address of box 1
	add	box2_r, fp, box2_s				//Calculates the base address of box 2

	mov	x0, box1_r					//Move the address of box 1 into the argument
	bl	newBox						//Call newBox(), also saves the result in b
	mov	box1_r, x0					//Moves the created box into box1_r

	mov	x0, box2_r					//Move the address of box2 into the agrument
	bl	newBox						//Call the newBox() function
	mov	box2_r, x0					//Moves the created box into box2_r

	//Printing intial message
	adrp	x0, fmt
	add	x0, x0, :lo12:fmt
	bl	printf

	mov	x1, box1_r					//Moves box1 base address into argument 2
	adrp	x0, s_first					//Moves "first" into first argument
	add	x0, x0, :lo12:s_first
	bl	printBox

	mov	x1, box2_r					//Moves box2 base address into argument 2
	adrp	x0, s_second					//Moves "second" into argument 1
	add	x0, x0, :lo12:s_second
	bl	printBox

	//Using equal method/function
	mov	x0, box1_r					//Setting up first argument
	mov	x1, box2_r					//Setting up second argument
	bl	equal						//Calling the equal() function/method

	mov	x24, x0
	cmp	x24, TRUE					//Compares the value of the returned value from equal() to TRUE
	b.ne	printChange					//Branches to if, if the statment is true


	//Using move() function
	mov	x0, box1_r					//Moves base address of box1 into x0 for first argument
	mov	w1, -5						//Moves -5 into second argument
	mov	w2, 7						//Moves 7 into third argument
	bl	move						//Calls the move function/method

	//Using the expand() function
	mov	x0, box2_r					//Moves address of box into first argument
	mov	w1, 3						//Moves 3 into second argument
	bl	expand

printChange:
	//Printing changed box values message
	adrp	x0, fmt2
	add	x0, x0, :lo12:fmt2
	bl	printf

	adrp    x0, s_first                                     //Moves "first" into first argument
        add     x0, x0, :lo12:s_first
        mov     x1, box1_r                                      //Moves box1 base address into argument 2
        bl      printBox

        adrp    x0, s_second                                    //Moves "second" into argument 1
        add     x0, x0, :lo12:s_second
        mov     x1, box2_r                                      //Moves box2 base address into argument 2
        bl      printBox

	ldp	fp, lr, [sp], dealloc				//Deallocate memory from the stack
	ret							//Returns to the OS

newBox:
	stp	fp, lr, [sp, -16]!				//Allocating memory to stack
	mov	fp, sp						//Moving sp into fp to point at the same thing

	str	xzr, [x0, box_origin + point_x]			//Store the value in w20 into b.origin.x
	str     xzr, [x0, box_origin + point_y]			//Store the value in w20 into b.origin.y

	mov	w10, 1						//Move 1 into w20
	str	w10, [x0, box_size + dim_width]			//Store the value in w20 into b.size.width

	mov     w10, 1						//Move 1 into w20
        str     w10, [x0, box_size + dim_height]       		//Store the value in w20 into b.size.height

	ldr	w10, [x0, box_size + dim_width]			//Load the value of the width of the box into w20
	ldr	w11, [x0, box_size + dim_height]		//Load the value of the height of the box into w20
	mul	w10, w10, w11					//Perform width * height and put it into w20
	str	w10, [x0, box_area]				//Store the value of w20 into b.area

	ldp	fp, lr, [sp], 16				//Deallocate memory from stack
	ret							//Returns to the OS

move:
	stp	fp, lr, [sp, -16]!				//Allocating memory to the stack
	mov	fp, sp						//Moving sp into fp so they point to the same location

	ldr	w10, [x0, box_origin + point_x]			//Load the point x of the box
	add	w10, w10, w1						//Adds the second argument to the value retrieved in w20
	str	w10, [x0, box_origin + point_x]			//Stores this value back into memory

	ldr     w10, [x0, box_origin + point_y]         	//Load the point y of the box
        add     w10, w10, w2                                         //Adds the second argument to the value retrieved in w20
        str     w10, [x0, box_origin + point_y]		        //Stores this value back into memory

	ldp	fp, lr, [sp], 16				//Deallocate memory from stack
	ret							//Returns to the OS

expand:
	stp	fp, lr, [sp, -16]!				//Allocating memory to the stack
	mov	fp, sp						//Moving sp into fp so they point to the same location

	ldr	w10, [x0, box_size + dim_width]			//Loads the width of the box from memory and stores it in w20
	mul	w10, w10, w1					//Multiplies the value in w20 by factor (argument 2)
	str     w10, [x0, box_size + dim_width]                 //Stores the width of the box

	ldr     w11, [x0, box_size + dim_height]                //Loads the height of the box from memory and stores it in w21
	mul     w11, w11, w1                                    //Multiplies the value in w21 by factor (argument 2)
	str     w11, [x0, box_size + dim_height]                //Stores the width of the box

	mul	w12, w10, w11					//Multiplies the width by the height
	str	w12, [x0, box_area]				//Stores this value into the area from memory

	ldp	fp, lr, [sp], 16
	ret

printBox:
	stp	fp, lr, [sp, -16]!				//Allocating memory to stack
	mov	fp, sp						//Moves sp into fp so they point at the same address

	//Move parameter registers to temp registers
	mov	x10, x0
	mov	x11, x1

	//Printing box
	adrp	x0, fmt1
	add	x0, x0, :lo12:fmt1
	mov	x1, x10						//Move string into first argument
	//Moving values into arguments
	ldr	w2, [x11, box_origin + point_x]
	ldr	w3, [x11, box_origin + point_y]
	ldr	w4, [x11, box_size + dim_width]
	ldr	w5, [x11, box_size + dim_height]
	ldr	w6, [x11, box_area]
	bl	printf						//Calling printf function

	ldp	fp, lr, [sp], 16				//Deallocating memory
	ret							//Returning to the OS

equal:
	stp	fp, lr, [sp, -16]!				//Allocates memory in the stack
	mov	fp, sp						//Moves sp into fp so they point at the same location

	mov	x10, x0						//Stores the address of b1 into box_base_r
	mov	x11, x1						//Stores the address of b2 in box2_base_r

	mov	x0, FALSE					//Returns false if the boxes aren't equal

	b	if1						//Branches to first if statement

if1:
	ldr	w12, [x10, box_origin + point_x]		//Loads the point x value of b1
	ldr	w13, [x11, box_origin + point_x]		//Load the point x value of b2
	cmp	w12, w13					//Compares the values of x of b1 and b2
	b.ne	false						//Branches to second if, if they are equal

if2:
	ldr     w12, [x10, box_origin + point_y]	        //Loads the point y value of b1
        ldr     w13, [x11, box_origin + point_y]        	//Load the point y value of b2
	cmp     w12, w13                                        //Compares the values of y of b1 and b2
        b.ne    false	                                        //Branches to third if, if they are equal

if3:
	ldr     w12, [x10, box_size + dim_width]        	//Loads the width value of b1
        ldr     w13, [x11, box_size + dim_width]        	//Load the width value of b2
        cmp     w12, w13                                        //Compares the width of b1 and b2
        b.ne    false                                           //Branches to fourth if, if they are equal
if4:
	ldr     w12, [x10, box_size + dim_height]	        //Loads the height value of b1
        ldr     w13, [x11, box_size + dim_height]       	//Load the height value of b2
        cmp     w12, w13                                        //Compares the height of b1 and b2
        b.ne    exitEqual                                       //Branches to to exit if they are not equal

	mov	x0, TRUE					//Returns true when function called
	b	exitEqual

false:
	mov	x0, FALSE					//Returns FALSE when function called
exitEqual:
	ldp     fp, lr, [sp], 16	                        //Dealloctaes memory in stack
        ret							//Returns to OS
