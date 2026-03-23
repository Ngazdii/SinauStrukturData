import tkinter as tk
from tkinter import ttk, messagebox
import pyttsx3
import threading
import time

# DATA
queue = []
nomor = 1


# TTS
def speak(text):
    def run():
        engine = pyttsx3.init()
        engine.setProperty('rate', 150)
        engine.say(text)
        engine.runAndWait()
    threading.Thread(target=run).start()


# FUNCTION
def ambil_antrian():
    global nomor
    nama = entry_nama.get()

    if nama == "":
        messagebox.showwarning("Warning", "Nama harus diisi!")
        return

    queue.append((nomor, nama))
    listbox.insert("", "end", values=(nomor, nama))


    nomor += 1
    entry_nama.delete(0, tk.END)


def panggil_antrian():
    if len(queue) == 0:
        messagebox.showinfo("Info", "Antrian kosong")
        return

    no, nama = queue.pop(0)
    listbox.delete(listbox.get_children()[0])

    label_nomor.config(text=str(no))
    label_nama.config(text=nama)

    speak(f"Nomor antrian {no}, atas nama {nama}, silakan menuju loket.")


def tampilkan_data():
    if len(queue) == 0:
        messagebox.showinfo("Info", "Antrian kosong")
    else:
        data = "\n".join([f"{n} - {nm}" for n, nm in queue])
        messagebox.showinfo("Data Antrian", data)


# GUI SETUP
root = tk.Tk()
root.title("Antrian Bank")
root.geometry("600x550")
root.configure(bg="#eef5ff")

style = ttk.Style()
style.theme_use("default")

# STYLE BUTTON
style.configure("TButton",
                font=("Segoe UI", 10, "bold"),
                padding=10)

# HEADER
header = tk.Label(root, text="SISTEM ANTRIAN BANK",
                  font=("Segoe UI", 18, "bold"),
                  bg="#eef5ff", fg="#0d47a1")
header.pack(pady=10)

# DISPLAY BESAR
frame_display = tk.Frame(root, bg="#eef5ff")
frame_display.pack(pady=10)

label_nomor = tk.Label(frame_display, text="-",
                       font=("Segoe UI", 50, "bold"),
                       fg="#1565c0", bg="#eef5ff")
label_nomor.pack()

label_nama = tk.Label(frame_display, text="-",
                      font=("Segoe UI", 14),
                      bg="#eef5ff")
label_nama.pack()

# INPUT
frame_input = tk.Frame(root, bg="#eef5ff")
frame_input.pack(pady=10)

tk.Label(frame_input, text="Nama:", bg="#eef5ff").grid(row=0, column=0, padx=5)
entry_nama = ttk.Entry(frame_input, width=25)
entry_nama.grid(row=0, column=1, padx=5)

# BUTTON
frame_btn = tk.Frame(root, bg="#eef5ff")
frame_btn.pack(pady=10)

ttk.Button(frame_btn, text="Ambil Antrian", command=ambil_antrian).grid(row=0, column=0, padx=10)
ttk.Button(frame_btn, text="Panggil Antrian", command=panggil_antrian).grid(row=0, column=1, padx=10)
ttk.Button(frame_btn, text="Tampilkan Data", command=tampilkan_data).grid(row=0, column=2, padx=10)

# TABLE
columns = ("Nomor", "Nama")
listbox = ttk.Treeview(root, columns=columns, show="headings", height=10)

listbox.heading("Nomor", text="Nomor")
listbox.heading("Nama", text="Nama")

listbox.column("Nomor", width=80, anchor="center")
listbox.column("Nama", width=200)

listbox.pack(pady=10)

# RUN
root.mainloop()