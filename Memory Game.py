import random as r
import os
import time
import msvcrt
import getpass

number = []

check = open("highScore.txt", "r")
scores = check.read().split("\n")
check.close()

highscore = 0
name = "Nobody"

def clear():
    os.system('cls' if os.name == 'nt' else 'clear')

def addRandom(num):
    for i in range(num):
        num = str(r.randint(0, 9))
        number.append(num)

def prettyPrint():
    for num in number:
        print(num, end="")
    print()

def gameBegin():
    highScore = open("highScore.txt", "a")

    clear()

    temp = 1
    score = 0
    level = 1
    seconds = 3

    name = input("What Is Your Name?\n> ")

    clear()
    while True:
        clear()

        addRandom(temp)

        print(f"Level {level}\n\nThe Number Is:")
        prettyPrint()


        time.sleep(seconds)
        while msvcrt.kbhit():
            clear()
            flush = getpass.getpass(prompt = "If Nothing Shows Up Press Enter")
        clear()
        guess = input("Input Your Guess: ")

        combined_number = ''.join(number)

        if guess == combined_number:
            print("Correct")
            score += 1
            time.sleep(1)
            temp += 1
            number.clear()
            if score % 3 == 0:
                seconds += 1.5

        else:
            clear()
            print(f"Dang, You Got It Wrong\nCorrect Number: {combined_number}\nYou Guessed:    {guess}")
            next = input("\nPress Enter To Continue")
            break

        level += 1
    clear()
    if score > highscore:
        print("Congrats! You Beat The High Score!\n")
    print(f"{name.strip().title()}'s Score Was: {score}\n")

    space_to_underscore = name.replace(" ", "_")
    highScore.write(f"{space_to_underscore} {score}\n")
    highScore.close()

print("Welcome To The Memory Game!!\n\nIn This Game, There Will Be A Number That Grows Every Turn\nYour Task Is To Guess However Many Numbers You Can Remember\nYou Will Have 3 Seconds To Remember The Number\nWith Every 3 Rounds You Gain An Extra 1.5 Seconds")
print()

while True:

    check = open("highScore.txt", "r")
    scores = check.read().split("\n")
    check.close()

    for rows in scores:
        data = rows.split()
        if data != []:
            if int(data[1]) > highscore:
                highscore = int(data[1])
                name = data[0]

    underscore_to_space = name.replace("_", " ")
    print(f"\nThe Current High Score Is {underscore_to_space.title()} With A Score Of {highscore}\n")
    menu = input("\nReady To Start?\n\n1.) Yes\n2.) No\n> ")
    clear()


    if menu == "1":
        gameBegin()
        again = input("Play Again? y/n\n> ")
        if again.lower().strip() == "y":
            number.clear()
            for rows in scores:
                data = rows.split()
                if data != []:
                    if int(data[1]) > highscore:
                        highscore = int(data[1])
                        name = data[0]
            continue
        else:
            print("Okay, Thanks For Playing!!")
            break
    elif menu == "2":
        print("It's okay, take your time")