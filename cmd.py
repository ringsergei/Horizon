import os
import time

def compiling():
	files = os.listdir("source")
	os.chdir('source')
	for file in files:
		if '.java' in file:
			try:
				os.system('javac {}'.format(file))
				print('<! Successful compiled >> ', file)
			except:
				print('<! Error >> ', file)
				continue

compiling()
time.sleep(5)