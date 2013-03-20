/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.concurrent;

/**
 * Chains an exception to desired type. For example: </p>
 * <p/>
 * <pre>
 * ExceptionChainer&lt;SomeException&gt; chainer = new ExceptionChainer&lt;SomeException&gt;()
 * {
 *     public SomeException chain(Throwable t)
 *     {
 *         if (t instanceof SomeException)
 *             return (SomeException) t;
 *         else
 *             return new SomeExcepion(t);
 *     }
 * };
 * </pre>
 *
 * @param <Z> Throwable type
 */
public interface ExceptionChainer<Z extends Throwable> {

    Z chain(Throwable t);

}