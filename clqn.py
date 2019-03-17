import os
import time

def cleaning():
	files = os.listdir("source")
	for file in files:
		if ".class" in file:
			print("<! Successful removed >> ", file)
			os.unlink("source/{}".format(file))
cleaning()
time.sleep(5);