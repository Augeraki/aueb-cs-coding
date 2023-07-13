##Vasileios Avgerakis am: P3210013
# Read me : perimente ta antistoixa mynhmata prin eisagete pedia kai afoy eisagete pathste enter
	.data
string: .asciiz "enter a number:"
str2: .asciiz "select *,+,-,/,% "
op: .space 1
zero: .asciiz "divided by 0"
msg: .asciiz "divided by 0"
msg_input: .asciiz "wrong input exiting program \n"
Loop: .asciiz " Do you want to repeat [y=1/n=0]? " 
done: .asciiz "result= "
UI: .asciiz "Wait for messages before inputing numbers and operators.\n"
	.text
	.globl __go
__go:
	
	la $a0,UI
	li $v0,4
	syscall
yes: 
	la $a0, string # enter a number msg
	li $v0, 4
	syscall
	#arxiko input 
	li $v0, 5
	syscall
	#sw $v0, x #input a number
	
	move $s0, $v0
	
	
	
operator:
	la $a0 , str2 # enter operator msg
	li $v0, 4
	syscall
	la $a0,op
	li $v0,8
	syscall
	#input operator
	#sb $v0,op
	
	lb $t6,op
	#move $t6,$v0
	   
	
	beq $t6, 37, mod
	beq $t6, 47, divide
	beq $t6, 43, addit #ascii value of + is 43
	beq $t6, 45, minus# ascii value of - is ... etc
	beq $t6, 42, multiple
	beq $t6, 61, result 
	j error
	
mod:
	la $a0, string #enter num msg
	li $v0, 4
	syscall
	sub $v0,$v0,$v0# arxikopoiw ton v0=0.
	li $v0, 5      #enter num
	syscall
	#x mod 0 is undefined 
	move $t0,$v0
	
	beq $t0,$zero,divzero
	rem $s3, $s0, $t0
	move $s0,$s3
	
	j operator
	
divide:
	
	la $a0, string 
	li $v0, 4
	syscall
	sub $v0,$v0,$v0
	li $v0, 5
	syscall
	
	move $t0,$v0
	
	beq $t0,$zero,divzero
	div $s3, $s0, $t0
	move $s0,$s3
	j operator
addit:

	la $a0, string 
	li $v0, 4
	syscall
	sub $v0,$v0,$v0
	li $v0, 5
	syscall
	
	move $t0,$v0
	
	
	add $s3, $s0, $t0
	move $s0,$s3
	j operator
	
minus:

	la $a0, string 
	li $v0, 4
	syscall
	sub $v0,$v0,$v0
	li $v0, 5
	syscall
	
	move $t0,$v0
	
	
	sub $s3, $s0, $t0
	move $s0,$s3
	j operator
	
multiple:
	
	# only 32 bit results 
	la $a0, string 
	li $v0, 4
	syscall
	sub $v0,$v0,$v0
	li $v0, 5
	syscall
	
	move $t0,$v0
	
	
	mul $s3, $s0, $t0
	move $s0,$s3#eg 1+2-1=3-1=2 $s0=1 + 2 $s3=3 $s0=3 -1 
	j operator
result:
	la $a0 , done
	li $v0, 4
	syscall
	move $a0,$s3
	li $v0, 1
	syscall
	la $a0, Loop
	li $v0, 4
	syscall
	#la $a0,ans
	li $v0 , 5
	syscall
	move $t0,$v0
	beq $t0,$zero,exit
	
	
	
	j init
error: 
	#sub $v0,$v0,$v0
	la $a0,msg_input
	li $v0,4
	syscall
	j exit
divzero:
	la $a0,msg
	li $v0,4
	syscall
	
	
exit: 
	li $v0 , 10
	syscall
init:
	sub $s0,$s0,$s0
	sub $s3,$s3,$s3#initiliazing saved registers that where used previously
	j yes
