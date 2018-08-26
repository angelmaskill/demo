package com.bytecode.asm;

import java.lang.reflect.Field;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.bytecode.utils.ByteArrayClassLoader;
import com.bytecode.utils.CountService;

public class AsmDynamicProxyPerformanceTest {

	public static CountService createAsmBytecodeDynamicProxy(CountService delegate) throws Exception {
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		String className = CountService.class.getName() + "AsmProxy";
		String classPath = className.replace('.', '/');
		String interfacePath = CountService.class.getName().replace('.', '/');
		classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, classPath, null, "java/lang/Object",
				new String[] { interfacePath });

		MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
		initVisitor.visitCode();
		initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
		initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		initVisitor.visitInsn(Opcodes.RETURN);
		initVisitor.visitMaxs(0, 0);
		initVisitor.visitEnd();

		FieldVisitor fieldVisitor = classWriter.visitField(Opcodes.ACC_PUBLIC, "delegate", "L" + interfacePath + ";",
				null, null);
		fieldVisitor.visitEnd();

		MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "count", "()I", null, null);
		methodVisitor.visitCode();
		methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
		methodVisitor.visitFieldInsn(Opcodes.GETFIELD, classPath, "delegate", "L" + interfacePath + ";");
		methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, interfacePath, "count", "()I");
		methodVisitor.visitInsn(Opcodes.IRETURN);
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();

		classWriter.visitEnd();
		byte[] code = classWriter.toByteArray();
		CountService bytecodeProxy = (CountService) new ByteArrayClassLoader().getClass(className, code).newInstance();
		Field filed = bytecodeProxy.getClass().getField("delegate");
		filed.set(bytecodeProxy, delegate);
		return bytecodeProxy;
	}

}