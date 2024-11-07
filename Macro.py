macro_definition_table = {} # Storing all macro definitions with their names
macro_name_table = {} # Storing macro names and their index

def process_pass1(source_code):
  mdt_index = 0
  macro_definition = []
  current_macro_name = None
  inside_macro = False

  for line in source_code:
    tokens = line.strip().split() # store each word of the line of source code

    if (not tokens): # skips blank lines
      continue

    if (tokens[0] == 'MACRO'): # beginning of macro definition
      inside_macro = True
      continue

    if (inside_macro == True and tokens[0] == 'MEND'): # if end of macro is reached
      inside_macro = False
      macro_definition_table[current_macro_name] = macro_definition[:]
      macro_name_table[current_macro_name] = mdt_index
      mdt_index += len(macro_definition)
      macro_definition = []
      current_macro_name = None
      continue

    if (inside_macro == True): # processing contents of macro
      if (not current_macro_name):
        current_macro_name = tokens[0]
      macro_definition.append(line.strip())

      
def process_pass2(source_code):
  output = []
  inside_macro = False

  for line in source_code:
    tokens = line.strip().split()

    if (not tokens or tokens[0] == 'MACRO'): # skipping spaces, MACRO and MEND keywords
      inside_macro = True
      continue
    elif (tokens[0] == 'MEND'):
      inside_macro = False
      continue

    if inside_macro:
      continue

    macro_name = tokens[0]
    if macro_name in macro_name_table: # expand macro from source code
      args = tokens[1:]
      macro_def = macro_definition_table[macro_name]
      for expanded_line in macro_def:
        for i, arg in enumerate(args):
          expanded_line = expanded_line.replace(f"&ARG{i+1}", arg)
        output.append(expanded_line)
    else: # append line if not a macro
      output.append(line.strip())

  return output

def display():
  print("Macro Name Table (MNT):")
  for name, index in macro_name_table.items():
    print(f"Macro Name: {name} | Index: {index}")

  print("Macro Definition Table (MDT):")
  for name, definition in macro_definition_table.items():
    print(f"Macro: {name}")
    for line in definition:
      print(f"\t{line}")
      
source_code = [
    "MACRO",
    "INCR &ARG1",
    "ADD &ARG1, ONE",
    "MEND",
    "MACRO",
    "DECR &ARG1",
    "SUB &ARG1, ONE",
    "MEND",
    "START",
    "INCR A",
    "DECR B",
    "END"
]

process_pass1(source_code)
display()
print("PASS 2:")
expanded_code = process_pass2(source_code)
for i in expanded_code:
  print(i)
