croatian = {'c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z='}

string = input()
count, i = 0, 0
while i < len(string):
    if {string[i:i+2],}.issubset(croatian):
        count += 1; i += 2
    elif string[i:i+3] == 'dz=':
        count += 1; i += 3
    else:
        count += 1; i+= 1
print(count)
