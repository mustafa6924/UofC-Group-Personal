//Equation is a^4 + b^2 + cx + d

define(x_r, x19)
define(y_r, x20)
define(a_r, x21)
define(b_r, x22)
define(c_r, x23)
define(d_r, x24)
define(square, x25)
define(power_4, x27)
define(initial_y, x26)
define(initial_x, x28)

fmt:    .string "At the value of x = %d y is minimum at y = %d\n"

        .balign 4
        .global main
main:   stp     x29, x30, [sp, 16]                                      //Main function, skeleton for the start
        mov     x29, sp

        mov     a_r, 6                                                  //Stores the value of a the coefficient of x^4
        mov     b_r, -333                                               //Stores the value of b the coefficient of x^2
        mov     c_r, -74                                                //Stores the value of c the coefficient of x
        mov     d_r, -23                                                //Stores the value of the constant d
        mov     x_r, -11                                                //Stores the initial value of x from the range
        mov     initial_y, 0                                            //Initializing the minimum y value
        mov     initial_x, 0                                            //initializing the minimum x value
        b       test                                                    //Goes to the loop

top:    mov     y_r, 0                                                  //Initializing the register for the minimum value of y

	add     y_r, y_r, d_r                                           //Add the value of d to the minimum

        mul     square, x_r, x_r                                        //Storing x^2 in a register
        mul     x12, square, x_r                                        //Storing the value of x^3 to get x^4
        mul     power_4, x12, x_r                                       //Storing the value of x^4 in a macro
        mul     x11, c_r, x_r						//Storing the value of -74x in a temp register

        add     y_r, x11, y_r                                           //Adding the constant and -74x together

        mul     x9, b_r, square                                         //Adding the x^2 part of the equation
        add     y_r, x9, y_r

        mul     x10, a_r, power_4                                       //Adding the x^4 part of the equation
        add     y_r, x10, y_r

	add	x_r, x_r, 1
        cmp     initial_y, y_r
        b.le	test
        mov     initial_y, y_r
        mov     initial_x, x_r
	add	initial_x, initial_x, -1				//Adds -1 to x as it was incremented before the if condition

test:   cmp     x_r, 9
        b.le    top

print:	adrp	x0, fmt
	add	x0, x0, :lo12:fmt
	mov	x1, initial_x
	mov	x2, initial_y
	b	printf

exit:   mov     x0, x0
        ldp     x29, x30, [sp, 16]
        ret
