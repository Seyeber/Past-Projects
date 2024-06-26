#include <iostream>
#include <string>
#include <vector>
#include <fstream>
using namespace std;

int main(){
    string mystr;
    ifstream myfile;
    int total_score = 0;
     myfile.open("Advent4.txt");
    if (myfile.is_open()){
        while (myfile.good()){
            getline(myfile, mystr);


            string temp_first;
            string temp_second;
            string add = "";
            int score_indiv = 0;

            vector <string> first_half = {};
            vector <string> second_half = {};

            int find = mystr.find(":");
            int find_second = mystr.find("|");

            for (int i = find + 1; i <= find_second - 1; i++){  
              temp_first += mystr[i];
            }

            for (int j = find_second + 1; j <= mystr.length() - 1; j++){
                temp_second += mystr[j];
            }

          for (int k = 1; k <= temp_first.length() - 1; k++){
            add = "";

            if (isspace(temp_first[k])){
              k++;
            }
            while (isdigit(temp_first[k])){
              add += temp_first[k];
              k++;
            }
            if (add != ""){
              first_half.push_back(add);
            }
          }

            
            for (int l = 1; l <= temp_second.length() - 1; l++){
              add = "";

              if (isspace(temp_second[l])){
                l++;
              }
              
              while (isdigit(temp_second[l])){
                add += temp_second[l];
                l++;
              }
              if (add != ""){
                second_half.push_back(add);
              }
            }

          for (auto w: first_half){
            for (auto un: second_half){
              if (un == w){
                if (score_indiv >= 1){
                  score_indiv = score_indiv * 2;
                }
                else{
                  score_indiv++;
                }
              }
            }
          }
          total_score += score_indiv;
        }
    }
    cout << "Score Is: " << total_score;
    return 0;
    myfile.close();
}