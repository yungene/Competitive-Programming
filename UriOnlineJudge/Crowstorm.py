#https://www.urionlinejudge.com.br/judge/en/problems/view/2203
def converter1(inpu):
    l1 = inpu.split(' ')
    global l2
    l2 = []
    for i in l1:
        l2.append(int(i))
    return l2

while True:
    try:
        i1 = input()
        i2 = converter1(i1)
    except:
        break

    xf=i2[0]
    yf=i2[1]
    xi=i2[2]
    yi=i2[3]
    vi=i2[4]
    r1=i2[5]
    r2=i2[6]

    radius = r1 + r2
    travelled = vi * 1.5
    distance = ((xf-xi)**2 + (yf-yi)**2)**0.5

    if radius >= distance + travelled:
        print('Y')
    else:
        print('N')

