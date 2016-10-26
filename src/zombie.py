import random
import sys

def gameFini():
	print('you have won the game')
	sys.exit()

def greenRand():
	green = ('brain','foot','brain','shotgun','brain','foot')
	y = random.choice(green)
	return y

def yellowRand():
	yellow = ('brain','foot','shotgun','shotgun','brain','foot')
	y = random.choice(yellow)
	return y

def redRand():
	red = ('brain','shotgun','shotgun','shotgun','brain','foot')
	y = random.choice(red)
	return y

def menu():
	print('1 | roll')
	print('2 | finish turn')
	print('3 | display score')
	x = int(input(' '))
	return x

def numusers():
	print(' ')
	print('input number of users')
	x = input('')
	return x

def nameInput(x):
	users = []
	while users.len() < numusers():
		users = 

#main
numusers = numusers()
numdice = 2

usernamehold = [numusers]
