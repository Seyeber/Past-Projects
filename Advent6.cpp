#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>
using namespace std;

int main(){
    string mystr;
    ifstream myfile;
    string temp;
    string all;
    int final_score = 1;
    stringstream in;
    long long num;

    vector <long long> time = {};
    vector <long long> distance = {};

    string printline;
    myfile.open("Advent6Data.txt");
    if (myfile.is_open()){
        while (myfile.good()){
            getline(myfile, mystr);
            all += mystr;
        }
        myfile.close();
    }
        int find_T = all.find('T');
        int find_D = all.find('D');

        for (int i = 0; i <= (all.length() - 1); i++){
            if (i < find_D){
                temp = "";
                while (isdigit(all[i])){
                    temp += all[i];
                    i++;
                }
                if (temp != ""){
                    time.push_back(stoi(temp));
                }
            }
            else if (i > find_D){
                temp = "";
                while (isdigit(all[i])){
                    temp += all[i];
                    i++;
                }
                if (temp != ""){
                    in  << temp;
                    in >> num;
                    distance.push_back(num);
                }
            }
        }
        int counter = 0;
        for (auto v: time){
            int temp_count = 0;
            cout << "Time: " << v << " Distance: " << distance[counter] << endl;
            //Formula: Time Held * (Time - Time Held)
            for (int j = v; j >= 0; j--){
                if (j * (v - j) > distance[counter]){
                    temp_count++;
                }
            }
            final_score = final_score * temp_count;
            counter++;
        }
        cout << "Final Score... " << final_score; 
}