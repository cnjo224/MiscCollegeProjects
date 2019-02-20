#!/usr/bin/python
#Author: Caitlin Jones
#Date: 9/24/18
#CS 316 Program 1: Space Travel Calculator
import cgi
import cgitb
import math

#function computes the distance with the law of cosines
def compute(x,y,a):
    #all of the unit conversions are handled, just do the math
    #cos(angle in radians)
    result = math.sqrt(math.pow(x,2) + math.pow(y,2) - (2*x*y*(math.cos(math.radians(a)))))
    #returns to convertAndCalculate function
    return result

#handles unit conversions and calls the compute function
def convertAndCalculate(anglea,unita,distx,unitx,disty,unity,unitanswer):
    #convert the unit of the angle to degrees
    if unita != "degree":
        convFactor, operator = getMultiplier(unita, 'degree')
        if operator == 'Error':
            print 'Unit conversion failed. Unit input not found'
            return
        elif operator == 'multiply':
            anglea *= convFactor
        else:
            anglea /= convFactor

    #convert unity into unitx, if needed
    if unitx != unity:
        convFactor, operator = getMultiplier(unitx, unity)
        if operator == 'Error':
            print 'Unit conversion failed. Unit input not found'
            return
        elif operator == 'multiply':
            unity = unity * convFactor
        else:
            unity = unity / convFactor

    #Perform the Cos Law calculation
    finalDistance = compute(distx, disty, anglea)

    #Convert the answer into the desired units
    if unitx != unitanswer:
        convFactor, operator = getMultiplier(unitx, unity)
        if operator == 'Error':
            print 'Unit conversion failed. Unit input not found'
            return
        elif operator == 'multiply':
            finalDistance = finalDistance * convFactor
        else:
            finalDistance = finalDistance / convFactor

    return finalDistance    

#create a dictionary of conversion units
def getMultiplier(unit1, unit2):
    #define a dictionary, search dictionary
    dictionary = {
        ("parsec", "lightyear") : 3.26,
        ("lightyear", "kilometer") : 9461000000000,
        ("xlarn", "parsec") : 6.3762,
        ("xlarn", "lightyear") : 20.786412,
        ("parsec", "kilometer"): 30860000000000 ,
        ("xlarn", "kilometer"): 196769532000000,

        ("degree", "radian") : 0.0174533,
        ("radian", "xarnian") : 100,
        ("xarnian", "degree") : 0.572958 
    }
    #Test if the keys are in the dictionary and whether the value is a multiplier or divider
    result = dictionary.get((unit1, unit2), "Error")
    result2 = dictionary.get((unit2, unit1), "Error") 
    if(result != "Error"):
        return result, 'multiply'
    elif(result2 != "Error"):
        return result2, 'divide'
    else:
        return 0, result

    #return values are number and string(mult,divide,error= not in dictionary)

#main function
def main():
    #get input from html page and do type checking
    print 'Content-type: text/html\n\n'
    cgitb.enable() #enable cgi debugging
    form = cgi.FieldStorage()
    try:
        #numbers must be converted from string to float
        anglea = float(form.getvalue('anglea'))
        unita = (form.getvalue('unita')).lower()
        distx = float(form.getvalue('distx'))
        unitx = (form.getvalue('unitx')).lower()
        disty = float(form.getvalue('disty'))
        unity = (form.getvalue('unity')).lower()
        unitanswer = (form.getvalue('unitanswer')).lower()
        #angles can't be negative
        if anglea <= 0 or distx <= 0 or disty <= 0 :
            raise Exception('Negative input values detected')
    except:
        #input types were wrong, output error message and end script
        print "Input Error. Try Different Values "
        return

    #normal input types (string and digit)
    result = convertAndCalculate(anglea,unita,distx,unitx,disty,unity,unitanswer)

    #Output the Resulting Information
    print "Origin (Distance from Earth: " + str(distx) + " " + unitx + "\nDestination (Distance from Earth: " + str(disty) + " " + unity + "\nAngle (between above vectors): " + str(anglea) + " " + unita + "\n\nAnswer: " + str(result) + " " + unitanswer
    #END OF SCRIPT

if __name__ == "__main__":
     main()
