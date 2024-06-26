import time, os, re  #Imports

user = [] #List

#Clears the Terminal
def clear():
    os.system('cls' if os.name == 'nt' else 'clear')

def effect(num):
    for num in range(num):
        clear()
        print("." * num)
        time.sleep(1)

#Determines if Password is Valid
def run(password):

    regex_special = re.compile('[@_!#$%^&*()<>?/\|}{~:]')

    regex_number = re.compile('[0-9]')

    regex_upper = re.compile('[ABCDEFGHIJKLMNOPQRSTUVWXYZ]')

    length = len(password)

    if (regex_special.search(password) == None or regex_number.search(password) == None or regex_upper.search(password) == None or length <= 11):
        if (regex_special.search(password) == None):
            print("Please Include A Special Character")
        if (regex_number.search(password) == None):
            print("Please Include A Number")
        if (regex_upper.search(password) == None):
            print("Please Include An Uppercase Letter")
        if length <= 11:
            print("Password Must Be 12 Characters Long")
    else:
        return True

#Creates User
def create_user():
    clear()

    while True:
        username = input("Choose A Username\n> ")

        if username in user:
            print("This Username is Already Taken")
            continue
        else:
            break

    while True:
        password = input("\nMake Sure Your Password Includes\n- A Special Character\n- A Number\n- An Uppercase Letter\n- 12 Characters Long\n\nEnter Password:\n> ")

        test_pass = run(password)
        if test_pass == False:
            run(password)
        elif test_pass == True:
            time.sleep(2)
            clear()
            break
        time.sleep(2)
        clear()

    check = input(f"""
    Username: {username}
    Password: {password}
    Is this Correct?
    > """)
    if check.lower() == "yes":
        user.append(username)
        user.append(password)
        effect(4)
        clear()
        print("Done!! Welcome:) ")
        time.sleep(1)

    else:
        create_user()

#Runs the Program
if __name__ == '__main__':

    while True:
        clear()
        console = input("Welcome To The Terminal:\n1. Create User\n2. Log On\n3. Delete Account\n> ")

        if console == "1":
            create_user()

        elif console == "2":
            username = input("Please Enter Username:\n> ")
            if username not in user:
                print("Invalid Username, Please Try Again")
                time.sleep(1)
                continue

            target_index = user.index(username)
            password = input("Please Enter Password:\n> ")

            if password == user[target_index + 1]:
                print(f"Welcome {username}")
                time.sleep(2)
            else:
                print("Invalid Password, Please Try Again")

        elif console == "3":

            username = input("Please Enter Username:\n> ")
            target_index = user.index(username)

            if username not in user:
                print("Invalid Username, Please Try Again")
                time.sleep(1)
                continue

            password = input("Please Enter Password:\n> ")

            if password == user[target_index + 1]:
                check = input(f"Are you sure {username}? ")
                if check.lower() == "yes":
                    user.remove(username)
                    user.remove(password)
                    effect(4)
                    print("Done")
                    time.sleep(1)
                else:
                    continue
            else:
                print("Invalid Password, Please Try Again")