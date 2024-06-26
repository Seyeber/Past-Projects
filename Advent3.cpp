#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
using namespace std;

int main(){
    string mystr;
    ifstream myfile;
    string together;
    int rowlength = 0;

    myfile.open("testdata.txt");
    if (myfile.is_open()){
        while (myfile.good()){
            getline(myfile, mystr);
            for (int i = 0; i < mystr.length(); i++){
                together += mystr[i];
            }
        }
    }
    rowlength = mystr.length();
    myfile.close();
    for (int j = 0; j < together.length(); j++){
        if (together[j] != '.' && !isdigit(together[j])){
            string temp;
            
            //Special then Digit
            if (isdigit(together[j+1])){
                temp = "";
                int test = j+1;
                while (isdigit(together[test])){
                    temp += together[test];
                    test++;
                }
                cout << temp << "\n";
            }
            
            //Digit Then Special
            if (isdigit(together[j-1])){
                temp = "";
                int test = j-1;
                while (isdigit(together[test])){
                    temp += together[test];
                    test--;
                }
                reverse(temp.begin(), temp.end());
                cout << temp << "\n";
            }

            //Special On Bottom (Still within the digits)
            
            if (isdigit(together[j-rowlength])){  
                temp = "";
                int test = j-rowlength;
                if (isdigit(together[test - 1])){
                    if (isdigit(together[test - 1])){
                        while(isdigit(together[test])){
                            temp += together[test];
                            test--;
                            reverse(temp.begin(), temp.end());
                        }
                    }
                }
                while (isdigit(together[test])){
                    temp += together[test];
                    test++;
                }
                cout << temp << "\n";
            }

            //Adjacent Below
            if (!isdigit(together[j - rowlength] && isdigit(together[j - (rowlength - 1)]) || isdigit(together[j - (rowlength + 1)]))){
                int test;
                temp = "";
                if (isdigit(together[j - (rowlength - 1)])){
                    test = j - (rowlength - 1);
                    while(isdigit(together[test])){
                        temp += together[test];
                        test++;
                    }
                    cout << temp << "\n";
                }
                if (isdigit(together[j - (rowlength - 2)])){
                    test = j - (rowlength + 1);
                    temp = "";
                    while(isdigit(together[test])){
                            temp += together[test];
                            test--;
                        }
                        reverse(temp.begin(), temp.end());
                        cout << temp << "\n";
                }
            }
        }
    }

    //cout << together;
    return 0;
}