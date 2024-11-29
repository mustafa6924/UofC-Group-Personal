fmt:	.string "At the value of x = %d y is minimum at y = %d\n"

        .balign 4
	.global main
main:	stp	x29, x30 , [sp, -16]
	mov	x29, sp

        mov	x19, 6			//Stores the coefficient for x^4
        mov	x20, -333		//Stores the coefficient for x^2
        mov	x21, -74		//Stores the coefficient for x
        mov	x22, -23		//Stores the constant -23 in the equation
        mov	x23, -11		//Stores the value of x
        mov	x17, 0			//Register to store the previous value of y before being reset
        mov	x16, 0			//Register to store the minimum value of y

test:	cmp	x23, 9
	b.gt	print

        mov	x24, 0			//Stores the current value of y and resets it after every iteration
        mov	x18, 0			//Temporary register
        mul	x25, x21, x23		//Stores the value of x multiply by -74
        mul	x26, x23, x23		//Stores the value of x^2
        mul	x27, x26, x23		//Stores the value of x^3
        mul	x28, x27, x23		//Stores the value of x^4
        add	x24, x22, x25		//Adds the constant and -74x

        mul	x18, x20, x26		//Finding the value of x^2
        add	x24, x24, x18		//Adding the value of x^2 to the y value

        mul	x18, x19, x28		//Finding the value of x^4
        add	x24, x24, x18		//Adding the value of x^4 to the y value

	add	x23, x23, 1
	cmp	x17, x24		//If condition for weather the new minimum of y is smaller than the previous
        b.le	test			//Goes to the start of the loop if condition not true

        mov	x17, x24		//Stores the previous value of y
	add	x16, x23, -1		//Subtracts 1 from the value of x as it has been incremented before the condition
	b	test			//Branches to loop


print:	adrp	x0, fmt			//Print function
	add	x0, x0, :lo12:fmt
        mov	x1, x16			//Stores the value of x register into x1 register
        mov	x2, x17			//Stores the value of y register into x2 register
        bl	printf


exit:	mov	x0, 0
	ldp	x29, x30, [sp,16]
	ret
