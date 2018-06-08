<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Relatórios</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo, ${usuario.nome}</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="${pageContext.request.contextPath}/ListagemClientes"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="${pageContext.request.contextPath}/ListagemGames"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemConsoles"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemAcessorios"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemActionFigures"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="${pageContext.request.contextPath}/ListagemUsuarios"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="${pageContext.request.contextPath}/ListagemFiliais"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="Vendas_ProcurarCliente.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="${pageContext.request.contextPath}/GerarRelatorios.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpoRelatorio">
            <table cellpadding="10">
                <c:forEach items="${Listagem}" var="venda">
                    <tr>
                        <td><strong>Cliente</strong></td>
                        <td><strong>Data Venda</strong></td>
                        <td><strong>Valor Total</strong></td>
                    </tr>
                    <tr>
                        <td>${venda.cliente.nome}</td>
                        <td>${venda.dataVenda}</td>
                        <td>R$ ${venda.total}</td>

                    </tr>

                    <tr>
                        <td><strong>Carrinho:</strong></td>
                    </tr>
                    <c:forEach items="${venda.carrinho}" var="itemcarrinho">


                        <tr>
                            <td>Produto: ${itemcarrinho.produto.nome}</td>
                            <td>Quantidade: ${itemcarrinho.quantidade}</td>
                            <td>Subtotal: R$ ${itemcarrinho.subtotal}</td>

                        </tr>
                    </c:forEach>

                    <tr>
                        <td colspan='3'>-------------------------------------------------------------------------------------------</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>