package gd.rf.theoneboringmancompany.growham.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gd.rf.theoneboringmancompany.growham.actors.play.Hamster;

public abstract class Serialization {
    public static void write(Hamster hamster) {
        FileHandle file = Gdx.files.local(Settings.Path.SERIALIZATION_FILE);
        try {file.writeBytes(serialize(hamster), false);}
        catch (Exception e) {e.printStackTrace();}
    }

    public static Hamster read() {
        Hamster hamster = null;
        FileHandle file = Gdx.files.local(Settings.Path.SERIALIZATION_FILE);
        try {hamster = (Hamster) deserialize(file.readBytes());}
        catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
        return hamster;
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream o = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return o.readObject();
    }

}