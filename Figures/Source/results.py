# Import libraries
import matplotlib.pyplot as plt
import numpy as np
import csv
 
 
# Creating dataset
all_data = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]]

folder = r'D:\Users\Usuario\OneDrive - UNIVERSIDAD SAN JORGE\PhD\PCT\Results\AllData.csv'

with open(folder, newline='') as csvfile:
    spamreader = csv.reader(csvfile, delimiter=';')
    line_count  = 0
    for row in spamreader:
        if line_count < 2:
            line_count += 1
        else:
            all_data[0].append(float(row[3].replace(",",".")))
            all_data[1].append(float(row[4].replace(",",".")))
            all_data[2].append(float(row[5].replace(",",".")))
            all_data[3].append(float(row[9].replace(",",".")))
            all_data[4].append(float(row[10].replace(",",".")))
            all_data[5].append(float(row[11].replace(",",".")))
            all_data[6].append(float(row[15].replace(",",".")))
            all_data[7].append(float(row[16].replace(",",".")))
            all_data[8].append(float(row[17].replace(",",".")))
            all_data[9].append(float(row[21].replace(",",".")))
            all_data[10].append(float(row[22].replace(",",".")))
            all_data[11].append(float(row[23].replace(",",".")))
            all_data[12].append(float(row[27].replace(",",".")))
            all_data[13].append(float(row[28].replace(",",".")))
            all_data[14].append(float(row[29].replace(",",".")))
            all_data[15].append(float(row[33].replace(",",".")))
            all_data[16].append(float(row[34].replace(",",".")))
            all_data[17].append(float(row[35].replace(",",".")))
            line_count +=1


argos = [all_data[6],all_data[7],all_data[8]]
vermis = [all_data[0],all_data[1],all_data[2]]
teuthus = [all_data[3],all_data[4],all_data[5]]
maia = [all_data[12],all_data[13],all_data[14]]
orion = [all_data[9],all_data[10],all_data[11]]
overall = [all_data[6]+all_data[0]+all_data[3]+all_data[12]+all_data[9],all_data[16],all_data[17]]

bosses = [vermis, teuthus, argos, orion, maia, overall]
labels = ['Simulation', 'Test', 'Baseline']
titles = ['VERMIS (Host)','TEUTHUS (Host)','ARGOS (Host)','ORION (Host)','MAIA (Host)','OVERALL']
bplots = []
fig, (ax1, ax2, ax3, ax4, ax5, ax6) = plt.subplots(nrows=1, ncols=6, figsize=(15, 5))
axes = [ax1, ax2, ax3, ax4, ax5, ax6]

# add the line on the desire position
for ax in axes:
    ax.axhline(1,color = 'r', linestyle='--', linewidth=5)
 
# rectangular box plot
for ax, title, boss in zip(axes, titles, bosses):
    bplots.append(ax.boxplot(boss, vert=True, patch_artist=True, labels=labels, widths=0.6))
    ax.set_title(title)
    if title == 'OVERALL':
        ax.set_facecolor("#F3F3F3") # background color

# fill with colors
colors = ['#CDF7DB', '#ADD7E7', '#FFD795']
for bplot in (bplots):
    for patch, color in zip(bplot['boxes'], colors): # the box color
        patch.set_facecolor(color)
    for patch, color in zip(bplot['fliers'], colors): # the dots color
        patch.set_markerfacecolor(color)

# adding horizontal grid lines
for ax in axes:
    ax.yaxis.grid(True)
    ax.set_ylim(0,1)

# show plot
plt.show()