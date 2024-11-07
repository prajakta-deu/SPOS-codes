
try:
    source = open('source.txt','r')
    data = source.read()
    print('File read successfully\n\n')
    source.seek(0)
except FileNotFoundError: #A little bit of exception handling
    print('\n\n\nFile Not found. Create a source.txt first.\n\n\n ')
except IOError:
    print('There was an IO error')
LT_index = 0 #index of LT table
ST_index = 0 #index of ST table
add = 0 # address in source code
MOT = {'STOP': '00','ADD': '01','SUB': '02','MULT': '03','MOVER': '04','MOVEM': '05','COMP': '06','BC': '07','DIV': '08','READ': '09','PRINT': '10','START': '01','END': '02','ORIGIN': '03','LTORG': '05','DS': '01','DC': '02','AREG,': '01','BREG,': '02','EQ':'01'}
ST = []
code=[]
LT=[]
MC = []
# LT, ST are lists of lists. code= intermediate code table


def classy(text):
    '''This function will return the class of the word to be inputted in the Intermediate table'''
    text = text.upper()
    if text in ['STOP','ADD','SUB', 'MULT','MOVER','MOVEM','COMP','BC','DIV','READ', 'PRINT']:
        return 'IS'
    elif text in ['START','END','ORIGIN','LTORG']:
        return 'AD'
    elif text in ['DS','DC']:
        return 'DL'
    elif text in ['AREG,','BREG,']: return 'RG'
    elif text in ['EQ']: return 'CC'
    else: return 'None'

def handle_start():
    '''This function gives you the starting address of the code'''
    line= source.readline()
    words=line.split()
    if words[0].upper()=='START':
        return int(words[1])
    else: 
        print("No Start Statement! Abort!\n")
        return 0

def pass1():
    add=handle_start()
    if not add:
        print("Ending Pass 1 due to Above error.")
        return
    global ST_index, LT_index  # to modify global variables, use global keyword
    while True:
        line=source.readline()# handlestart function reads line 1 and we start from the second line.
        if not line:
            break
        words= line.split()
        for w in words:
            w=w.upper()
            if w[0]=='=':
                entry=[LT_index,w, add]
                LT.append(entry)
                LT_index +=1 
            elif classy(w)== 'None':
                for term in ST:
                    if w== term[1]: break # I check if the label is already present in ST.
                else:
                    entry=[ST_index,w, add]
                    ST.append(entry)
                    ST_index +=1
        add+=1
    print('LT:')
    for a in LT:
        print(a)
    print('\n\n\nST:')
    for a in ST:
        print(a)
        
def intermediate():
    source.seek(0)
    while True:
        entry=[]
        ind = 0
        line=source.readline()
        if not line:
            break
        words=line.split()
        for w in words:
            w=w.upper()
            if classy(w)!='None': #it is a directive
                entry.append((classy(w),MOT[w]))
            elif w[0]== '=': #it is a literal.
                for a in LT:
                    if a[1]==w:
                        ind = a[0]
                        break
                entry.append(('L',ind))
            else: #it is a symbol
                for a in ST:
                    if a[1]==w:
                        ind = a[0]
                        break
                entry.append(('S',ind))
        code.append(entry)
    print("\nThe Intermediate code is:")
    for entry in code:
        print(entry)

pass1()
intermediate()
