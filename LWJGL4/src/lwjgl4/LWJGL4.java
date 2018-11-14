/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwjgl4;


import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.lang.Math;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.util.glu.Cylinder;
import java.io.IOException;                                                     // Librerias de captura de errores
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;



public class LWJGL4 {
    GLFWErrorCallback errorCallback;
    GLFWKeyCallback   keyCallback;
    GLFWFramebufferSizeCallback fbCallback;

    long window;
    int width = 800;
    int height = 600;
    float vueltas = (float) 0.0;
    float vueltas2 = (float) 0.0;
    float vueltas3 = (float) 0.0;
    float vueltas4 = (float) 0.0;
    // JOML matrices
    Matrix4f projMatrix = new Matrix4f();
    Matrix4f viewMatrix = new Matrix4f();
    Matrix4f modelMatrix = new Matrix4f();
    Matrix4f modelViewMatrix = new Matrix4f();
    
    //Matrix4f projMatrix2 = new Matrix4f();
    //Matrix4f viewMatrix2 = new Matrix4f();
    Matrix4f modelMatrix2 = new Matrix4f();
    Matrix4f modelViewMatrix2 = new Matrix4f();
    
    Matrix4f modelMatrix3 = new Matrix4f();
    Matrix4f modelViewMatrix3 = new Matrix4f();
    Matrix4f modelMatrix4 = new Matrix4f();
    Matrix4f modelViewMatrix4 = new Matrix4f();
     

    // FloatBuffer for transferring matrices to OpenGL
    FloatBuffer fb = BufferUtils.createFloatBuffer(16);

    void run() {
        try {
            init();
            loop();

            glfwDestroyWindow(window);
            keyCallback.free();
        } finally {
            glfwTerminate();
            errorCallback.free();
        }
    }

    void init() {
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure our window
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, "Juego Raul", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key,
                    int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, true);
            }
        });
        glfwSetFramebufferSizeCallback(window,
                fbCallback = new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int w, int h) {
                if (w > 0 && h > 0) {
                    width = w;
                    height = h;
                }
            }
        });

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width)/2 , (vidmode.height() - height)/2 );

        glfwMakeContextCurrent(window);
        glfwSwapInterval(0);
        glfwShowWindow(window);
    }
    
    
    
    
    

    void renderCube() {
        glBegin(GL_QUADS);
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glColor3f(   0.6f,  0.9f,  0.8f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glColor3f(   0.0f,  1.0f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glColor3f(   1.0f,  1.0f,  1.0f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glColor3f(   0.0f,  0.0f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glEnd();
    }
    void renderCube2() {
        glBegin(GL_QUADS);
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glColor3f(   0.6f,  0.9f,  0.8f);
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glColor3f(   0.0f,  1.0f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glColor3f(   0.0f,  0.0f,  0.0f );
        glVertex3f(  0.5f,  0.5f,  0.5f );
        glVertex3f(  0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f, -0.5f );
        glVertex3f( -0.5f,  0.5f,  0.5f );
        glColor3f(   0.0f,  0.0f,  0.0f );
        glVertex3f(  0.5f, -0.5f, -0.5f );
        glVertex3f(  0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f,  0.5f );
        glVertex3f( -0.5f, -0.5f, -0.5f );
        glEnd();
    }
    
    void renderCube3() {
        glBegin(GL_QUADS);
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f(  0.25f, -0.25f, -0.25f );
        glVertex3f( -0.25f, -0.25f, -0.25f );
        glVertex3f( -0.25f,  0.25f, -0.25f );
        glVertex3f(  0.25f,  0.25f, -0.25f );
        glColor3f(   1.0f,  0.0f,  0.0f);
        glVertex3f(  0.25f, -0.25f,  0.25f );
        glVertex3f(  0.25f,  0.25f,  0.25f );
        glVertex3f( -0.25f,  0.25f,  0.25f );
        glVertex3f( -0.25f, -0.25f,  0.25f );
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f(  0.25f, -0.25f, -0.25f );
        glVertex3f(  0.25f,  0.25f, -0.25f );
        glVertex3f(  0.25f,  0.25f,  0.25f );
        glVertex3f(  0.25f, -0.25f,  0.25f );
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f( -0.25f, -0.25f,  0.25f );
        glVertex3f( -0.25f,  0.25f,  0.25f );
        glVertex3f( -0.25f,  0.25f, -0.25f );
        glVertex3f( -0.25f, -0.25f, -0.25f );
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f(  0.25f,  0.25f,  0.25f );
        glVertex3f(  0.25f,  0.25f, -0.25f );
        glVertex3f( -0.25f,  0.25f, -0.25f );
        glVertex3f( -0.25f,  0.25f,  0.25f );
        glColor3f(   1.0f,  0.0f,  0.0f );
        glVertex3f(  0.25f, -0.25f, -0.25f );
        glVertex3f(  0.25f, -0.25f,  0.25f );
        glVertex3f( -0.25f, -0.25f,  0.25f );
        glVertex3f( -0.25f, -0.25f, -0.25f );
        glEnd();
    }
    
    void renderCube4() {
        glBegin(GL_QUADS);
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f(  0.25f, -0.25f, -0.25f );
        glVertex3f( -0.25f, -0.25f, -0.25f );
        glVertex3f( -0.25f,  0.25f, -0.25f );
        glVertex3f(  0.25f,  0.25f, -0.25f );
        glColor3f(   0.0f,  0.0f,  1.0f);
        glVertex3f(  0.25f, -0.25f,  0.25f );
        glVertex3f(  0.25f,  0.25f,  0.25f );
        glVertex3f( -0.25f,  0.25f,  0.25f );
        glVertex3f( -0.25f, -0.25f,  0.25f );
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f(  0.25f, -0.25f, -0.25f );
        glVertex3f(  0.25f,  0.25f, -0.25f );
        glVertex3f(  0.25f,  0.25f,  0.25f );
        glVertex3f(  0.25f, -0.25f,  0.25f );
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f( -0.25f, -0.25f,  0.25f );
        glVertex3f( -0.25f,  0.25f,  0.25f );
        glVertex3f( -0.25f,  0.25f, -0.25f );
        glVertex3f( -0.25f, -0.25f, -0.25f );
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f(  0.25f,  0.25f,  0.25f );
        glVertex3f(  0.25f,  0.25f, -0.25f );
        glVertex3f( -0.25f,  0.25f, -0.25f );
        glVertex3f( -0.25f,  0.25f,  0.25f );
        glColor3f(   0.0f,  0.0f,  1.0f );
        glVertex3f(  0.25f, -0.25f, -0.25f );
        glVertex3f(  0.25f, -0.25f,  0.25f );
        glVertex3f( -0.25f, -0.25f,  0.25f );
        glVertex3f( -0.25f, -0.25f, -0.25f );
        glEnd();
    }


     

    void loop() {
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.6f, 0.9f, 0.8f, 1.0f);  //el fondo
        // Enable depth testing
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
       
        // Remember the current time.
        long firstTime = System.nanoTime();

        while ( !glfwWindowShouldClose(window) ) {
            // Build time difference between this and first time. 
            long thisTime = System.nanoTime();
            float diff = (thisTime - firstTime) / 1E9f;
            // Compute some rotation angle.
            float angle = diff;

            // Make the viewport always fill the whole window.
            glViewport(0, 0, width, height);

            // Build the projection matrix. Watch out here for integer division
            // when computing the aspect ratio!
            projMatrix.setPerspective((float) Math.toRadians(40),
                                      (float)width/height, 0.01f, 100.0f);
            glMatrixMode(GL_PROJECTION);
            glLoadMatrixf(projMatrix.get(fb));

            // Set lookat view matrix
            viewMatrix.setLookAt(0.0f, 16.0f, 16.0f,     //la camara
                                 0.0f, 0.0f, 0.0f,
                                 0.0f, 1.0f, 0.0f);
            glMatrixMode(GL_MODELVIEW);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            for (int a = -3; a <= 4; a++) {
                for (int b = -3; b <= 4; b++) {
                    if((b==-2)&&(a==-2||a==0||a==2||a==4)){
                        modelMatrix3.translation(a * 1.0f, 1.0f, b * 1.0f);
                        if(vueltas3<34){
                            
                            modelMatrix3.rotateY(angle * (float) Math.toRadians(90));
                            
                            vueltas3+=0.0005;
                        }
                        glLoadMatrixf(viewMatrix.mul(modelMatrix3, modelViewMatrix3).get(fb));
                        renderCube3();
                    }else{
                        if((b==-3||b==-1)&&(a==-3||a==-1||a==1||a==3)){
                            modelMatrix3.translation(a * 1.0f, 1.0f, b * 1.0f);
                            if(vueltas3<34){
                                
                                modelMatrix3.rotateY(angle * (float) Math.toRadians(90));
                                
                                vueltas3+=0.0005;
                            }
                            glLoadMatrixf(viewMatrix.mul(modelMatrix3, modelViewMatrix3).get(fb));
                            renderCube3();
                         }
                    }
                     if((b==3)&&(a==-3||a==-1||a==1||a==3)){
                        modelMatrix4.translation(a * 1.0f, 1.0f, b * 1.0f);
                        if(vueltas4<34){
                            
                            modelMatrix4.rotateY(angle * (float) Math.toRadians(90));
                            
                            vueltas4+=0.0005;
                        }
                        glLoadMatrixf(viewMatrix.mul(modelMatrix4, modelViewMatrix4).get(fb));
                        renderCube4();
                    }else{
                        if((b==4||b==2)&&(a==-2||a==0||a==2||a==4)){
                            modelMatrix4.translation(a * 1.0f, 1.0f, b * 1.0f);
                            if(vueltas4<34){
                                
                                modelMatrix4.rotateY(angle * (float) Math.toRadians(90));
                                
                                vueltas4+=0.0005;
                            }
                            glLoadMatrixf(viewMatrix.mul(modelMatrix4, modelViewMatrix4).get(fb));
                            renderCube4();
                         }
                    }
                
                }
            }
            
            
            // Render some grid of cubes at different x and z positions
            for (int x = -3; x <= 4; x++) {
                for (int z = -3; z <= 4; z++) {
                    if(x==-2||x==0||x==2||x==4){
                        if(z==-2||z==-0||z==2||z==4){
                    modelMatrix.translation(x * 1.0f, 0, z * 1.0f);
                        if(vueltas<90){
                            modelMatrix.rotateY(angle * (float) Math.toRadians(90))
                                //.rotateZ(angle * (float) Math.toRadians(90))
                            ;
                            vueltas+=0.0005;
                    }
                    glLoadMatrixf(viewMatrix.mul(modelMatrix, modelViewMatrix).get(fb));
                    renderCube();
                    
                        }
                    }else{
                        if((x==-3||x==-1||x==1||x==3)&&(z==-3||z==-1||z==1||z==3)){
                             modelMatrix.translation(x * 1.0f, 0, z * 1.0f);
                             if(vueltas<90){
                                 modelMatrix.rotateY(angle * (float) Math.toRadians(90));
                                 vueltas+=0.0005;
                             }
                        }
                         glLoadMatrixf(viewMatrix.mul(modelMatrix, modelViewMatrix).get(fb));
                         renderCube();
                    }
                }
            }
            
            for (int w = -3; w <= 4; w++) {
                for (int v = -3; v <= 4; v++) {
                    if(w==-3||w==-1||w==1||w==3){
                         if(v==-2||v==0||v==2||v==4){
                    modelMatrix2.translation(w * 1.0f, 0, v * 1.0f);
                        if(vueltas2<90){
                            modelMatrix2.rotateY(angle * (float) Math.toRadians(90))
                                //.rotateZ(angle * (float) Math.toRadians(90))
                            ;
                            vueltas2+=0.0005;
                    }
                    
                    glLoadMatrixf(viewMatrix.mul(modelMatrix2, modelViewMatrix2).get(fb));
                    renderCube2();
                        }
                    }else{
                        if((w==-2||w==0||w==2||w==4)&&(v==-3||v==-1||v==1||v==3)){
                             modelMatrix2.translation(w * 1.0f, 0, v * 1.0f);
                             if(vueltas2<90){
                                 modelMatrix2.rotateY(angle * (float) Math.toRadians(90));
                                 vueltas2+=0.0005;
                             }
                        }
                         glLoadMatrixf(viewMatrix.mul(modelMatrix2, modelViewMatrix2).get(fb));
                         renderCube2();
                    }
                }
            }
            
            
            
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new LWJGL4().run();
    }
}
